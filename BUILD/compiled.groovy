def hostName = "https://github.com/shubhamdevops1"
//def CREDENTIALS_ID = "BitbucketSSH"
def repoName = "code-test"
def branchName = "master" 

String folderroot = 'BUILD-JOBS'
folder(folderroot){

}
// multibranchPipelineJob(folderroot + '/' + 'code'){
//     branchSources{
//         git {
//             id('1') // IMPORTANT: use a constant and unique identifier
//             remote('git clone git@bitbucket.org:shubham1714/code-test.git')
//             //credentialsId('BitbucketSSH')
//             includes('master')
//             excludes('')
//         }
//     }
//     orphanedItemStrategy {
//         discardOldItems {
//             numToKeep(20)
//         }
//     }
// }

pipelineJob(folderroot + '/' + 'build-job'){
    logRotator {
        numToKeep(5)
        artifactNumToKeep(1)
    }
    
    definition{
        cpsScm{
            scm{
                git{
                    remote{
                        url("${hostName}/${repoName}.git")
                        //credentials("${CREDENTIALS_ID}")
                    }
                    branch("${branchName}")
                }
            }
            scriptPath("Jenkinsfile")
        }
    }
}