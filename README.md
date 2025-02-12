# SpringBatchV5-Example
스프링배치5 실습 프로젝트

- resourcelessRepository 방식 실습 (JobLauncherApplcationRunner를 통해 Job을 실행하는 방식, JobLauncher를 통해 Job을 실행하는 방식)
   - 전자의 경우 JobParameter를 ApplicationArguments로 받아야 한다. ArgoWorkflow 에서 가져온 환경변수를 사용하는 행 표준을 지키기 어렵겠다
   - 후자의 경우 JobParameter를 개발자가 코드상에서 세팅하여 JobLauncher에 전달한다. 여기서 JobParameter를 생성할 때 ArgoWorkflow에서 가져온 환경변수를 세팅할 수 있기에 행 표준을 지키기 용이하겠다
