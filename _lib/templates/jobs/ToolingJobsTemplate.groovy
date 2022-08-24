package templates.jobs

class ToolingJobsTemplate{
    def static hostName = "git clone git@github.com:shubhamdevops1"
    // as we use the public github hence we do not need to mentioned keys.
    //def static CREDENTIALS_ID = ""

    def static BRANCH_NAME = "master"
    def static JENKINS_FILR = "Jenkinsfile"    

    static def createJobWithBranchExtension(job, repoName, projectName, branchName = "main", fileName = "Jenkinsfile", jobRotation = 3){
        job.with{
            logRotator{
                numToKeep(jobRotation)
            }
            definition{
                cpsScm{
                    scm{
                        git{
                            remote{
                                url("${hostName}/${repoName}.git")
                                //Commenting out the keys
                                //credentials(CREDENTIALS_ID)
                            }
                            branch("${branchName}")
                        }
                    }
                    scriptPath("${fileName}")
                }
            }
        }
        return job
    }
}