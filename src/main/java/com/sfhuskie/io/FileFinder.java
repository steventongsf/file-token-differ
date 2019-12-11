package com.sfhuskie.io;
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
import java.nio.file.Paths;
import java.util.stream.Stream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileFinder {
    public Stream<Path> find(String baseDir, String fileExtension) throws IOException {
        Path basePath = Paths.get(baseDir);
        int maxDepth = 200;
        Stream<Path> stream =
                Files.find(basePath, maxDepth,
                        (path, basicFileAttributes) -> {
                            File file = path.toFile();
                            return !file.isDirectory() && file.getName().endsWith(fileExtension);
                        });
        return stream;
    }
}
