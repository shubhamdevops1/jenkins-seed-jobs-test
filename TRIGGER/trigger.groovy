def folderName = 'TRIGGER'

folder(folderName){
    displayName("TRIGGER-JOB")
}

def hostName = "git@bitbucket.org:shubham_devops_cloud"
def CREDENTIALS_ID = "BitbucketSSH"
def repoName = "devops-common"
def branchName = "master" 

pipelineJob(folderName + "/" + "triggerJob") {
    definition{
        cpsScm{
            scm{
                git{
                    remote{
                        url("${hostName}/${repoName}.git")
                        credentials(CREDENTIALS_ID)
                    }
                    branch("${branchName}")
                }
            }
            scriptPath("jobs/triggerJob.groovy")
        }
    }
}