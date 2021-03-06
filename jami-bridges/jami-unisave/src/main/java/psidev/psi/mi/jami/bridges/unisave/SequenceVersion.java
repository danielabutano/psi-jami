/**
 * Copyright 2008 The European Bioinformatics Institute, and others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package psidev.psi.mi.jami.bridges.unisave;

/**
 * Represent a sequence version: fasta + sequence version number.
 *
 */
public class SequenceVersion {
    
    private FastaSequence sequence;
    private int version;

    public SequenceVersion( FastaSequence sequence, int version ) {
        this.sequence = sequence;
        this.version = version;
    }

    public FastaSequence getSequence() {
        return sequence;
    }

    public int getVersion() {
        return version;
    }
}
