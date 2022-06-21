# HostChecker

## 개발 아이디어 

CommandLineRunner 인터페이스를 이용하여 스프링 부트 앱 구동 시 등록된 호스트 IP를 이용해서 
InetAddress.isReachable() 결과값을 데이터베이스에 업데이트. 이때, 동시성(멀티 쓰레딩)을 활용