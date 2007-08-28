package com.gerbildrop.groovy.ant

public class AntBuild {
    public AntBuild(String name) {
        def src = "../app/src/com/${name}";
        def classes = "../classes"
        def pages = "../sites/${name}";
        def preWar = "../preWar/${name}";
        def war = "../war/${name}";
        def preWarLibs = preWar + "/WEB-INF/lib";

        run(src,
            classes,
            pages,
            preWar,
            war,
            preWarLibs,
            name);
    }

    public run(String src,
               String classes,
               String pages,
               String preWar,
               String war,
               String preWarLibs,
               String name) {

        def ant = new AntBuilder();

        ant.delete(dir: preWar, failOnError:false);
        ant.mkdir(dir: preWar);
        ant.mkdir(dir: war);

        def cp = ant.path {
            fileset(dir: "../../currentLibs") {
                include(name:"*.jar");
            }
        //    pathelement(path:"");
        }
        //System.setProperty("java.home", "c:/jdk15")
        //
        //ant.echo(System.getProperty("java.home"))
        //
        //ant.echo(System.getenv("JAVA_HOME"))

        ant.javac(srcdir:src, destdir:classes, classpath:cp);
        ant.jar(destfile: war + "/" + name + ".jar", basedir: classes, includes: '*/**')


        ant.mkdir(dir: preWarLibs);

        ant.copy(todir: preWarLibs, file:war + "/" + name + ".jar")

        ant.copy(todir: preWar) {
            fileset ( dir: "../sites/" + name, includes : '*/**' )
        }

        ant.copy(todir: preWarLibs) {
            fileset ( dir: "../libs", includes : '*/**' )
        }

        ant.copy(todir: preWar + "/WEB-INF", file:"../web.xml")

        ant.war(destfile: war + "/squad66.war", basedir: preWar, includes: '*/**', webxml:preWar + "/WEB-INF/web.xml")
    }
}

//def ant = new AntBuilder()
//
//// lets just call one task
//ant.echo("hello")
//
//// here is an example of a block of Ant inside GroovyMarkup
//ant.sequential {
//    echo("inside sequential")
//    myDir = "../app/"
//    mkdir(dir:myDir)
//    copy(todir:myDir) {
//        fileset(dir:"../app/test") {
//            include(name:"**/*.groovy")
//        }
//    }
//
//    javac(srcdir:myDir, destdir:"../classes", classpath:"../libs")
//
//    echo("done")
//}
//
//// now lets do some normal Groovy again
//file = new File("build.xml")
//assert file.exists()