

/**
 *
 *
 * $ID:$
 * $COPYRIGHT:$
 *
 * @author torr
 * @since Aug 25, 2007 - 11:09:52 AM
 *
 */
ant = new AntBuilder();

def makedir = {directoryname ->
    ant.mkdir(dir: "$directoryname/app/src/com/gerbildrop/$directoryname");
    ant.mkdir(dir: "$directoryname/app/groovy/com/gerbildrop/$directoryname");
    ant.mkdir(dir: "$directoryname/app/test/com/gerbildrop/$directoryname");
    ant.mkdir(dir: "$directoryname/doc");
    ant.mkdir(dir: "$directoryname/dist");
}

makedir "console"
makedir "engine"
makedir "errorHandling"
makedir "gc"
makedir "gl"
makedir "j2ee"
makedir "jdbc"
makedir "loader"
makedir "logging"
makedir "lua"
makedir "mask"
makedir "messaging"
makedir "screenwriting"
makedir "serialization"
makedir "scripting"
makedir "util"
makedir "xml"