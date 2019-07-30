def lib = library (identifier: 'HadoopApiTools@master', retriever: modernSCM([$class: 'GitSCMSource', credentialsId: 'dbd9bf1b-0fd7-45da-b7c8-a7c3c3e03a23', remote: 'https://github.com/ilcatos/HadoopApiTools.git']))




//tool = new HadoopApiTools()

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

        def info1 = lib.getStatusOfDir(testUrl, testUserName, testDirname)
        println(info1)

    }
    
    stage('Put Files In Hdfs'){

        def info2 = lib.PutFilesInHdfs(testUrl,testUserName,testDirname,testPath,testFile)
        println(info2)
    }

}