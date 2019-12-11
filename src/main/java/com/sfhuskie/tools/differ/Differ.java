package com.sfhuskie.tools.differ;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.codehaus.plexus.util.FileUtils;

/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
/**
 * @author Steven Tong
 * 
 */
import com.sfhuskie.*;
import com.sfhuskie.io.FileFinder;

public class Differ {
    
    String baselineDir;
    String changedDir;
    List<String> symbolsUnChangedFiles;
    List<String> symbolsChangedFiles;
    
    public Differ(String baselineDir, String changedDir) {
        this.baselineDir = (new File(baselineDir)).getAbsolutePath();
        this.changedDir = (new File(changedDir)).getAbsolutePath();
    }
    public int diff() throws Exception {
        FileFinder ff = new FileFinder();
        symbolsUnChangedFiles = new ArrayList<String>();
        symbolsChangedFiles = new ArrayList<String>();
        Stream<Path> changedFiles =ff.find(this.changedDir, ".java");
        changedFiles.forEach(
            f -> {
                String baseLineFile = f.toString().replace(this.changedDir, this.baselineDir);
                String modifiedFile = f.toString();
                String s1, s2;
                try {
                    s1 = com.sfhuskie.io.FileUtils.readFileAsString(baseLineFile);
                    s2 = com.sfhuskie.io.FileUtils.readFileAsString(modifiedFile);
                    s1 = com.sfhuskie.io.FileUtils.removeAllTabsAndSpaces(s1);
                    s2 = com.sfhuskie.io.FileUtils.removeAllTabsAndSpaces(s2);
                } 
                catch (IOException e) {
                    throw new RuntimeException("Failed to read file",e );
                }
                if (s1.equals(s2)) {
                    this.symbolsUnChangedFiles.add(modifiedFile);
                }
                else {
                    this.symbolsChangedFiles.add(modifiedFile);
                }
            }
        );
        int fileCount = this.symbolsUnChangedFiles.size() + this.symbolsChangedFiles.size();
        console("Directory          1: "+this.baselineDir);
        console("Directory          2: "+this.changedDir);
        console("Summary");
        console("Entry                  Count");
        console("-------------------------------");
        console("reviewed files      : "+fileCount);
        console("unchanged files     : "+this.symbolsUnChangedFiles.size());
        console("symbol changed files: "+this.symbolsChangedFiles.size());
        console("-------------------------------");
        console("Violations");
        this.symbolsChangedFiles.forEach(f -> console("WARNING Symbol changed in "+f));
        console("-------------------------------");
        return this.symbolsChangedFiles.size();
    }
    static void usage() {
        console("Usage: [command] [directory 1] [directory 2] ");
        System.exit(1);
    }
    
    public static void main(String[] args) throws Exception {
        if (args.length < 2) {
            usage();
        }
        if (!FileUtils.fileExists(args[0])) {
            console("argument 1 is not a valid directory: "+args[0]);
            System.exit(1);
        }
        if (!FileUtils.fileExists(args[1])) {
            console("argument 2 is not a valid directory: "+args[1]);
            System.exit(1);
        }
        Differ d = new Differ(args[0], args[1]);
        System.exit(d.diff());
    }    
    static void console(String m) {
        System.out.println(m);
    }
}






