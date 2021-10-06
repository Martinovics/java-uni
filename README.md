# Java Project Template

Rename all *java_project_template* to a **unique** project name. 

**Compile java**: (javac *".java files to compile"*)
- javac package/name_1/*java package/name_2/*java package/name_3/*java
- **build args**: None, -build, -b

**Run java**:
- java package.name.Class *(where main function can be found)*

**Compile jar**:   (jar cf  *"Jar"*.jar *".class files to compile"*)
- jar cf *"Jar"*.jar package/name_1/*class package/name_2/*class
- - **build args**: -jar, -j

**Run jar**:
- java -cp *"Jar"*.jar package.name.Class

**Compile with MANIFEST**: (jar cfm *"Jar"*.jar MANIFEST.mf *".class files to compile"*)
- create MANIFEST.mf with content of "Main-Class: package.name.Class\n"
- jar cfm *"Jar"*.jar MANIFEST.mf package/name_1/*class package/name_2/*class
- **build args**: -manifest, -m
   - for new manifest file add: -newmanifest, -nm

**Run jar with MANIFEST**:
- java -jar *"Jar"*.jar