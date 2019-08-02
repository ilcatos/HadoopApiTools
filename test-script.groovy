def lib = library identifier: 'example@master', retriever: modernSCM([$class: 'GitSCMSource', credentialsId: 'dbd9bf1b-0fd7-45da-b7c8-a7c3c3e03a23', remote: 'https://github.com/ilcatos/HadoopApiTools.git', traits: [[$class: 'jenkins.plugins.git.traits.BranchDiscoveryTrait']]])

String testUrl = "http://quickstart.cloudera:14000"
String testDirname = "project-1-tmp"
String testUserName = "cloudera-scm"
String testPath = "/home"
String testFile = "job.xml"

node {
    stage('Prepare work dir '){
        sh 'ls -al'
    }

    stage('Get status of directory in HDFS') {

        def info1 = lib.by.devops.hadoop.lib.HadoopApiTools.new().getStatusOfDir(testUrl, testUserName, testDirname)
        println("???????????????????????????????????????????????")
        println(info1)
        println("???????????????????????????????????????????????")

    }
    
    stage('Put Files In Hdfs'){

        def info2 = lib.by.devops.hadoop.lib.HadoopApiTools.new().PutFilesInHdfs(testUrl,testUserName,testDirname,testPath,testFile)
        println("-----------------------------------------------")
        println(info2)
        println("-----------------------------------------------")
    }

}
