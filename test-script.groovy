import HadoopApiTools

tool = new HadoopApiTools()

String testUrl = "http://quickstart.cloudera:14000"
String testDirname = "project-1-tmp"
String testUserName = "cloudera-scm"
String testPath = "/home/"
String testFile = "config.xml"




node {
    stage('Prepare work dir '){
        sh 'ls -al'
    }

    stage('Get status of directory in HDFS') {

        tool.getStatusOfDir(testUrl, testUserName, testDirname)
        println "-------------------------------------------------"
        println(getStatusOfDir(testUrl, testUserName, testDirname))

    }
    
    stage('Put Files In Hdfs'){

        def info = tool.PutFilesInHdfs(testUrl,testUserName,testDirname,testPath,testFile)
        println(info)
    }

}
