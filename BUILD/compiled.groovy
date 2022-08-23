def hostName = "git@github.com:shubhamdevops1"
//def CREDENTIALS_ID = "BitbucketSSH"
def repoName = "code-test"
def branchName = "main" 

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

pipelineJob(folderroot + '/' + 'build'){
    logRotator {
        numToKeep(2)
        artifactNumToKeep(2)
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