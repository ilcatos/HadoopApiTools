package by.devops.hadoop.lib

import groovy.json.JsonSlurper

    def createDir(String httpfs_url, String hadoop_user, String hdfs_dirname) {
    // also must add permission part : [&permission=<OCTAL>]
        def info = sh (
                script:  "curl --negotiate -X PUT -u : '${httpfs_url}/webhdfs/v1/user/${hadoop_user}/${hdfs_dirname}?op=MKDIRS&user.name=${hadoop_user}'",
                returnStdout: true).trim()

        def infoOutput = new JsonSlurper().parseText(info)

        if (infoOutput.FileStatuses == null) {

            return infoOutput.RemoteException

        } else {

            return infoOutput.boolean

        }

    }

    def getStatusOfDir(String httpfs_url, String hadoop_user, String hdfs_dirname) {
/*
        def process = ['bash', '-c', "curl --negotiate -u : \"${httpfs_url}/webhdfs/v1/user/${hadoop_user}/${hdfs_dirname}/?op=LISTSTATUS\""].execute()
        process.waitFor()
        def info = new JsonSlurper().parseText(process.text)
 */
        def info = sh (
                script:  "curl --negotiate -u : '${httpfs_url}/webhdfs/v1/user/${hadoop_user}/${hdfs_dirname}/?op=LISTSTATUS'",
                returnStdout: true).trim()

        def infoOutput = new JsonSlurper().parseText(info)

        if (infoOutput.FileStatuses == null) {

            return infoOutput.RemoteException

        } else {

            return infoOutput.FileStatuses

        }
    }


    def PutFilesInHdfs(String httpfs_url, String hadoop_user, String hdfs_dirname, String PathTofile, String file) {
/*
        def process = ['bash', '-c', "curl --negotiate -X PUT -L -u : \"${httpfs_url}/webhdfs/v1/user/${hadoop_user}/${hdfs_dirname}/${file}?op=CREATE&&user.name=${hadoop_user}\"  --header \'Content-Type: application/octet-stream\' -T ${PathTofile}\${file} "].execute()
        process.waitFor()
        def info = new JsonSlurper().parseText(process.text)
 */

        def info = sh (
                script:  "curl --negotiate -X PUT -L -u : \"${httpfs_url}/webhdfs/v1/user/${hadoop_user}/${hdfs_dirname}/${file}?op=CREATE&&user.name=${hadoop_user}\"  --header  \"Content-Type: application/octet-stream\" -T ${PathTofile}/${file}",
                returnStdout: true).trim()

        def infoOutput = new JsonSlurper().parseText(info)

        return infoOutput

/*
        if (infoOutput.FileStatuses == null) {

            return infoOutput.RemoteException.message

        } else {

            return infoOutput.FileStatuses

        }


 */
    }