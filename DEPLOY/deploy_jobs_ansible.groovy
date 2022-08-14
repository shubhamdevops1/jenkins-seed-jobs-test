def folderroot = 'DEPLOY-JOBS'
def folderName = folderroot + "/ansible"

folder(folderName){
    displayName("ANSIBLE")
}

def hostName = "git@bitbucket.org:shubham_devops_cloud"
def CREDENTIALS_ID = "BitbucketSSH"
def repoName = "ipt-ansible-code"
def branchName = "master" 
def cluster = "np-devkops1"

pipelineJob(folderName + "/" + "deploy_feature_image") {
    parameters{
        choiceParam('REPONAME', ['ipt-ansible-code'], 'Ansible repo name')
        choiceParam('NAMESPACE', ['dev'], 'Kubernetes Namespace')
        stringParam("kubeContext", cluster, "Kubernetes context to use")
    }

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
            scriptPath("jobs/Jenkinsfile")
        }
    }
    
}