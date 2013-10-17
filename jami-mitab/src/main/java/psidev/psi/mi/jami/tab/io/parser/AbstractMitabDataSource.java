package psidev.psi.mi.jami.tab.io.parser;

import psidev.psi.mi.jami.datasource.FileSourceContext;
import psidev.psi.mi.jami.datasource.InteractionSource;
import psidev.psi.mi.jami.datasource.MIFileDataSource;
import psidev.psi.mi.jami.datasource.SourceCategory;
import psidev.psi.mi.jami.exception.MIIOException;
import psidev.psi.mi.jami.factory.MIDataSourceFactory;
import psidev.psi.mi.jami.listener.MIFileParserListener;
import psidev.psi.mi.jami.model.*;
import psidev.psi.mi.jami.tab.extension.*;
import psidev.psi.mi.jami.tab.listener.MitabParserListener;
import psidev.psi.mi.jami.utils.MIFileDatasourceUtils;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * abstract class for Mitab datasource
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>21/06/13</pre>
 */

public abstract class AbstractMitabDataSource<T extends Interaction, P extends Participant, F extends Feature> implements MIFileDataSource, InteractionSource, MitabParserListener{

    private static final Logger logger = Logger.getLogger("AbstractMitabDataSource");
    private MitabLineParser<T,P,F> lineParser;
    private boolean isInitialised = false;

    private URL originalURL;
    private File originalFile;
    private InputStream originalStream;
    private Reader originalReader;

    private Boolean isValid = null;

    private MitabParserListener parserListener;
    private MIFileParserListener defaultParserListener;

    /**
     * Empty constructor for the factory
     */
    public AbstractMitabDataSource(){
    }

    public AbstractMitabDataSource(File file) throws IOException {

        initialiseFile(file);
        isInitialised = true;
    }

    public AbstractMitabDataSource(InputStream input) {

        initialiseInputStream(input);
        isInitialised = true;
    }

    public AbstractMitabDataSource(Reader reader) {

        initialiseReader(reader);
        isInitialised = true;
    }

    public AbstractMitabDataSource(URL url) {

        initialiseURL(url);
        isInitialised = true;
    }

    public MIFileParserListener getFileParserListener() {
        return this.defaultParserListener;
    }

    public void initialiseContext(Map<String, Object> options) {
        if (options == null && !isInitialised){
            throw new IllegalArgumentException("The options for the Mitab interaction datasource should contain at least "+ MIDataSourceFactory.INPUT_OPTION_KEY + " to know where to read the interactions from.");
        }
        else if (options == null){
            return;
        }
        else if (options.containsKey(MIDataSourceFactory.INPUT_OPTION_KEY)){
            Object input = options.get(MIDataSourceFactory.INPUT_OPTION_KEY);
            if (input instanceof URL){
               initialiseURL((URL) input);
            }
            else if (input instanceof File){
                initialiseFile((File) input);
            }
            else if (input instanceof InputStream){
                initialiseInputStream((InputStream) input);
            }
            else if (input instanceof Reader){
                initialiseReader((Reader) input);
            }
            // suspect a file/url path
            else if (input instanceof String){
                String inputString = (String)input;
                SourceCategory category = MIFileDatasourceUtils.findSourceCategoryFromString(inputString);
                switch (category){
                    // file uri
                    case file_URI:
                        try {
                            initialiseFile(new File(new URI(inputString)));
                        } catch (URISyntaxException e) {
                            throw new IllegalArgumentException("Impossible to open and read the file " + inputString, e);
                        }
                        break;
                    // we have a url
                    case URL:
                        try {
                            initialiseURL(new URL(inputString));
                        } catch (MalformedURLException e) {
                            throw new IllegalArgumentException("Impossible to open and read the URL " + inputString, e);
                        }
                        break;
                    // we have a file
                    default:
                        initialiseFile(new File(inputString));
                        break;

                }
            }
            else {
                throw new IllegalArgumentException("Impossible to read the provided input "+input.getClass().getName() + ", a File, InputStream, Reader, URL or file/URL path was expected.");
            }
        }
        else if (!isInitialised){
            throw new IllegalArgumentException("The options for the Mitab interaction datasource should contain at least "+ MIDataSourceFactory.INPUT_OPTION_KEY + " to know where to read the interactions from.");
        }

        if (options.containsKey(MIDataSourceFactory.PARSER_LISTENER_OPTION_KEY)){
            setMIFileParserListener((MIFileParserListener) options.get(MIDataSourceFactory.PARSER_LISTENER_OPTION_KEY));
        }

        isInitialised = true;
    }

    public void close() throws MIIOException{
        if (isInitialised){

            if (this.originalStream != null){
                try {
                    this.originalStream.close();
                } catch (IOException e) {
                    throw new MIIOException("Impossible to close the original stream", e);
                }
                finally {
                    this.originalFile = null;
                    this.originalURL = null;
                    this.originalStream = null;
                    this.originalReader = null;
                    this.lineParser = null;
                    this.parserListener = null;
                    this.defaultParserListener = null;
                    isInitialised = false;
                    isValid = null;
                    isInitialised = false;
                }
            }
            else if (this.originalReader != null){
                try {
                    this.originalReader.close();
                } catch (IOException e) {
                    throw new MIIOException("Impossible to close the original reader", e);
                }
                finally {
                    this.originalFile = null;
                    this.originalURL = null;
                    this.originalStream = null;
                    this.originalReader = null;
                    this.lineParser = null;
                    this.parserListener = null;
                    this.defaultParserListener = null;
                    isInitialised = false;
                    isValid = null;
                    isInitialised = false;
                }
            }
            else{
                this.originalFile = null;
                this.originalURL = null;
                this.originalStream = null;
                this.originalReader = null;
                this.lineParser = null;
                this.parserListener = null;
                this.defaultParserListener = null;
                isInitialised = false;
                isValid = null;
                isInitialised = false;
            }
        }
    }

    public void reset() throws MIIOException{
        if (isInitialised){
            this.originalFile = null;
            this.originalURL = null;
            this.originalStream = null;
            this.originalReader = null;
            this.lineParser = null;
            this.parserListener = null;
            this.defaultParserListener = null;
            isInitialised = false;
            isValid = null;
            isInitialised = false;
        }
    }

    public boolean validateSyntax(MIFileParserListener listener) {
        setMIFileParserListener(listener);
        return validateSyntax();
    }

    public boolean validateSyntax() {
        if (!isInitialised){
            throw new IllegalStateException("The Mitab interaction datasource has not been initialised. The options for the Mitab interaction datasource should contain at least "+ MIDataSourceFactory.INPUT_OPTION_KEY + " to know where to read the interactions from.");
        }

        if (lineParser.hasFinished() && isValid == null){
            isValid = true;
        }

        if (isValid != null){
            return isValid;
        }

        // read the datasource
        Iterator<T> interactionIterator = getInteractionsIterator();
        while(interactionIterator.hasNext()){
            interactionIterator.next();
        }
        // if isValid is not null, it means that the file syntax is invalid, otherwise, we say that the file syntax is valid
        if (isValid == null){
            isValid = true;
        }
        return isValid;
    }

    public void onInvalidSyntax(FileSourceContext context, Exception e) {
        isValid = false;
        if (defaultParserListener != null){
            defaultParserListener.onInvalidSyntax(context, e);
        }
    }

    public void onSyntaxWarning(FileSourceContext context, String message) {
        if (defaultParserListener != null){
            defaultParserListener.onSyntaxWarning(context, message);
        }
    }

    public void onMissingCvTermName(CvTerm term, FileSourceContext context, String message) {
        if (defaultParserListener != null){
            defaultParserListener.onMissingCvTermName(term, context, message);
        }
    }

    public void onMissingInteractorName(Interactor interactor, FileSourceContext context) {
        if (defaultParserListener != null){
            defaultParserListener.onMissingInteractorName(interactor, context);
        }
    }

    public void onSeveralCvTermsFound(Collection<? extends CvTerm> terms, FileSourceContext context, String message) {
        if (defaultParserListener != null){
            defaultParserListener.onSeveralCvTermsFound(terms, context, message);
        }
    }

    public void onSeveralHostOrganismFound(Collection<? extends Organism> organisms, FileSourceContext context) {
        if (defaultParserListener != null){
            defaultParserListener.onSeveralHostOrganismFound(organisms, context);
        }
    }

    public void onParticipantWithoutInteractor(Participant participant, FileSourceContext context) {
        isValid = false;
        if (defaultParserListener != null){
            defaultParserListener.onParticipantWithoutInteractor(participant, context);
        }
    }

    public void onInteractionWithoutParticipants(Interaction interaction, FileSourceContext context) {
        isValid = false;
        if (defaultParserListener != null){
            defaultParserListener.onInteractionWithoutParticipants(interaction, context);
        }
    }


    public void onMissingInteractorIdentifierColumns(int line, int column, int mitabColumn) {
        isValid = false;
        if (parserListener != null){
            parserListener.onMissingInteractorIdentifierColumns(line, column, mitabColumn);
        }
    }

    public void onSeveralOrganismFound(Collection<MitabOrganism> organisms) {
        if (parserListener != null){
            parserListener.onSeveralOrganismFound(organisms);
        }
    }

    public void onSeveralStoichiometryFound(Collection<MitabStoichiometry> stoichiometry) {
        if (parserListener != null){
            parserListener.onSeveralStoichiometryFound(stoichiometry);
        }
    }

    public void onSeveralFirstAuthorFound(Collection<MitabAuthor> authors) {
        if (parserListener != null){
            parserListener.onSeveralFirstAuthorFound(authors);
        }
    }

    public void onSeveralSourceFound(Collection<MitabSource> sources) {
        if (parserListener != null){
            parserListener.onSeveralSourceFound(sources);
        }
    }

    public void onSeveralCreatedDateFound(Collection<MitabDate> dates) {
        if (parserListener != null){
            parserListener.onSeveralCreatedDateFound(dates);
        }
    }

    public void onSeveralUpdatedDateFound(Collection<MitabDate> dates) {
        if (parserListener != null){
            parserListener.onSeveralUpdatedDateFound(dates);
        }
    }

    public void onTextFoundInIdentifier(MitabXref xref) {
        if (parserListener != null){
            parserListener.onTextFoundInIdentifier(xref);
        }
    }

    public void onTextFoundInConfidence(MitabConfidence conf) {
        if (parserListener != null){
            parserListener.onTextFoundInConfidence(conf);
        }
    }

    public void onMissingExpansionId(MitabCvTerm expansion) {
        if (parserListener != null){
            parserListener.onMissingExpansionId(expansion);
        }
    }

    public void onSeveralUniqueIdentifiers(Collection<MitabXref> ids) {
        if (parserListener != null){
            parserListener.onSeveralUniqueIdentifiers(ids);
        }
    }

    public void onEmptyUniqueIdentifiers(int line, int column, int mitabColumn) {
        isValid = false;
        if (parserListener != null){
            parserListener.onEmptyUniqueIdentifiers(line, column, mitabColumn);
        }
    }

    public Iterator<T> getInteractionsIterator() {
        if (!isInitialised){
            throw new IllegalStateException("The Mitab interaction datasource has not been initialised. The options for the Mitab interaction datasource should contain at least "+ MIDataSourceFactory.INPUT_OPTION_KEY + " to know where to read the interactions from.");
        }
        // reset parser if possible
        if (lineParser.hasFinished()){
           reInit();
        }
        return createMitabIterator();
    }

    protected MitabLineParser<T,P,F> getLineParser() {
        return lineParser;
    }

    protected void setLineParser(MitabLineParser<T,P,F> lineParser) {
        this.lineParser = lineParser;
        this.lineParser.setParserListener(this);
    }

    protected abstract void initialiseMitabLineParser(Reader reader);

    protected abstract void initialiseMitabLineParser(File file);

    protected abstract void initialiseMitabLineParser(InputStream input);

    protected abstract void initialiseMitabLineParser(URL url);

    protected abstract Iterator<T> createMitabIterator();

    protected void reInit() throws MIIOException{
        if (isInitialised){
            if (this.originalFile != null){
                // close the previous stream
                if (this.originalStream != null){
                    try {
                        this.originalStream.close();
                    } catch (IOException e) {
                        logger.log(Level.SEVERE, "Could not close the inputStream.", e);
                    }
                }
                // reinitialise mitab parser
                try {
                    this.originalStream = new BufferedInputStream(new FileInputStream(this.originalFile));
                    this.lineParser.ReInit(this.originalStream);

                } catch (FileNotFoundException e) {
                    throw new MIIOException("We cannot open the file " + this.originalFile.getName(), e);
                }
            }
            else if (this.originalURL != null){
                // close the previous stream
                if (this.originalStream != null){
                    try {
                        this.originalStream.close();
                    } catch (IOException e) {
                        logger.log(Level.SEVERE, "Could not close the inputStream.", e);
                    }
                }
                // reinitialise mitab parser
                try {
                    this.originalStream = originalURL.openStream();
                    this.lineParser.ReInit(this.originalStream);

                } catch (IOException e) {
                    throw new MIIOException("We cannot open the URL " + this.originalURL.toExternalForm(), e);
                }
            }
            else if (this.originalStream != null){
                // reinit line parser if inputStream can be reset
                if (this.originalStream.markSupported()){
                    try {
                        this.originalStream.reset();
                        this.lineParser.ReInit(this.originalStream);

                    } catch (IOException e) {
                        throw new MIIOException("The inputStream has been consumed and cannot be reset", e);
                    }
                }
                else {
                    throw new MIIOException("The inputStream has been consumed and cannot be reset");
                }
            }
            else if (this.originalReader != null){
                // reinit line parser if reader can be reset
                if (this.originalReader.markSupported()){
                    try {
                        this.originalReader.reset();
                        this.lineParser.ReInit(this.originalReader);
                    } catch (IOException e) {
                        throw new MIIOException("The reader has been consumed and cannot be reset", e);
                    }
                }
                else {
                    throw new MIIOException("The reader has been consumed and cannot be reset");
                }
            }
        }
    }

    private void initialiseReader(Reader reader) {
        if (reader == null){
            throw new IllegalArgumentException("The reader cannot be null.");
        }
        this.originalFile = null;
        this.originalReader = reader;
        this.originalStream = null;
        this.originalURL = null;

        initialiseMitabLineParser(reader);
    }

    private void initialiseInputStream(InputStream input) {
        if (input == null){
            throw new IllegalArgumentException("The input stream cannot be null.");
        }
        this.originalFile = null;
        this.originalReader = null;
        this.originalStream = input;
        this.originalURL = null;

        initialiseMitabLineParser(input);
    }

    private void initialiseFile(File file)  {
        if (file == null){
            throw new IllegalArgumentException("The file cannot be null.");
        }
        else if (!file.canRead()){
            throw new IllegalArgumentException("Does not have the permissions to read the file "+file.getAbsolutePath());
        }
        this.originalFile = file;
        this.originalReader = null;
        this.originalStream = null;
        this.originalURL = null;

        initialiseMitabLineParser(file);
    }

    private void initialiseURL(URL url)  {
        if (url == null){
            throw new IllegalArgumentException("The url cannot be null.");
        }
        this.originalURL = url;
        this.originalReader = null;
        this.originalStream = null;
        this.originalFile = null;

        initialiseMitabLineParser(url);
    }

    protected void setOriginalFile(File originalFile) {
        this.originalFile = originalFile;
    }

    protected void setOriginalStream(InputStream originalStream) {
        this.originalStream = originalStream;
    }

    protected void setOriginalReader(Reader originalReader) {
        this.originalReader = originalReader;
    }

    protected void setOriginalURL(URL originalURL) {
        this.originalURL = originalURL;
    }

    protected void setMitabFileParserListener(MitabParserListener listener) {
        this.parserListener = listener;
        this.defaultParserListener = listener;
    }

    protected void setMIFileParserListener(MIFileParserListener listener) {
        if (listener instanceof MitabParserListener){
            setMitabFileParserListener((MitabParserListener) listener);
        }
        else{
            this.parserListener = null;
            this.defaultParserListener = listener;
        }
    }
}
