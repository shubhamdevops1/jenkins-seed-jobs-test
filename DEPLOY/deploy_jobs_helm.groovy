sourceFile = readFileFromWorkspace("_lib/templates/jobs/ToolingJobsTemplate.groovy")
jobs = new GroovyClassLoader(getClass().getClassLoader()).parseClass(sourceFile)

def folderroot = 'DEPLOY-JOBS'
def folderName = folderroot + "/helm"

folder(folderName){
    displayName("HELM")
}

jobs.createJobWithBranchExtension(pipelineJob(folderName + "/deploy_feature_image"), "ipt-charts-code", "HELMDEPL", "main", fileName = "Jenkinsfile.deploy").parameters{
    choiceParam('REPO
    
    NAME', ['ipt-charts-code'], 'Helm chart repo name')
    textParam{
        name('FEATUREIMAGE')
        defaultValue("")
        description('Enter the image name which you want to deploy')
    }
    stringParam('JIRA_TICKET', "", 'Jira Ticket')
    choiceParam('NAMESPACE', ['dev'], 'Kubernetes Namespace')
}