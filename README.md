# HostChecker

## 개발 아이디어 

CommandLineRunner 인터페이스를 이용하여 스프링 부트 앱 구동 시 등록된 호스트 IP를 이용해서 
InetAddress.isReachable() 결과값을 데이터베이스에 업데이트. 이때, 동시성(멀티 쓰레딩)을 활용

modified_date 컬럼값이 가장 오래된 것부터 ThreadWorker.java에서 호스트 정보 업데이트하도록 변경(Repository에서 nativeQuery 사용)

https://stackoverflow.com/questions/4779367/problem-with-isreachable-in-inetaddress-class를 참고해서 
AliveChecker.java에 checkIfAvailable2라는 메서드를 만들고 기존 InetAddress.isReachable()를 이용한 메서드는 
checkIfAvailable1라고 명칭을 정하여 둘 중 한 개에서 "Alive"가 리턴되면 "Alive"인 것으로 정함


## REST API 설명

    GET /hosts/{ipOrName} 

        설명 

            ip 혹은 (host)name이 주어지면 해당 호스트 정보를 조회

        Curl 

            curl -X GET --header 'Accept: application/json' 'http://localhost:8080/hosts/naver'

        Request URL 

            http://localhost:8080/hosts/naver

        Request Headers 

            {
                "Accept: "application/json"
            }

        Response Body

            {
                "name": "naver",
                "ip": "223.130.200.107",
                "createdDate": "2022-06-24T18:50:00", 
                "modifiedDate": "2022-06-24T18:50:00", 
                "isAlive": "false", 
                "lastAliveTime": null
            }

        Response Code

            200


    GET /hosts

        설명 

            등록된 모든 호스트의 정보 조회

        Curl 

            curl -X GET --header 'Accept: application/json' 'http://localhost:8080/hosts'

        Request URL 

            http://localhost:8080/hosts

        Request Headers 

            {
                "Accept: "application/json"
            }

        Response Body

            [
                {
                    "name": "EC2",
                    "ip": "",
                    "createdDate": "2022-06-24T18:38:35",
                    "modifiedDate": "2022-06-24T20:59:43",
                    "isAlive": "true",
                    "lastAliveTime": "2022-06-24T20:59:43"
                },
                {
                    "name": "naver",
                    "ip": "223.130.200.107",
                    "createdDate": "2022-06-24T18:50:00",
                    "modifiedDate": "2022-06-24T18:50:00",
                    "isAlive": "false",
                    "lastAliveTime": null
                }
            ]

        Response Code

            200


    PUT /hosts/{ipOrName}

        설명 

            ip 혹은 (host)name을 이용해 해당 호스트 정보 수정

        Curl 

            curl -X PUT http://localhost:8080/hosts/naver -H 'Content-Type: application/json' --data-raw '{"name": "naverr", "ip": "223.130.200.107"}'

        Request URL 

            http://localhost:8080/hosts/naver

        Request Headers 

            {
                "Accept: "application/json"
            }

        Response Body

            {
                "name": "naverr",
                "ip": "223.130.200.107",
                "createdDate": "2022-06-24T18:50:00",
                "modifiedDate": "2022-06-24T21:15:25.163",
                "isAlive": "false",
                "lastAliveTime": null
            }

        Response Code

            200


    DELETE /hosts/{ipOrName} 

        설명 

            ip 혹은 (host)name을 이용해 해당 호스트 삭제

        Curl 

            curl -X DELETE http://localhost:8080/hosts/naverr --header 'Accept: application/json'

        Request URL 

            http://localhost:8080/hosts/naverr

        Request Headers 

            {
                "Accept: "application/json"
            }

        Response Body

            {
                "result": "SUCCESS",
                "name": "DELETE"
            }

        Response Code

            200

    
    POST /hosts

        설명

            호스트 등록

        Curl 

            curl -X POST http://localhost:8080/hosts -H 'Content-Type: application/json' --data-raw '{"name": "naver", "ip": "223.130.200.107"}'

        Request URL 

            http://localhost:8080/hosts

        Request Headers 

            {
                "Accept: "application/json"
            }

        Response Body

            {
                "name":  "naver",
                "ip": "223.130.200.107",
                "createdDate": "2022-06-24T21:20:00.721",
                "modifiedDate": "2022-06-24T21:20:00.721",
                "isAlive": "false",
                "lastAliveTime": null 
            }

        Response Code

            200


## 참고

https://linked2ev.github.io/database/2021/04/15/MariaDB-3.-MariaDB-%EC%84%A4%EC%B9%98-for-Mac/

    기존 homebrew로 mysql 설치된 상태에서 제거 후 mariadb 설치하고 mysql.sock 관련 에러 발생
    (서버 실행이 안 되어 있거나, my.cnf, symbolic link 경로 등과 관련된 문제라고 함)
    위 링크 참고해서 해결

        brew services stop mysql (mariadb)

        brew unlink mysql (mariadb)
        brew remove mysql (mariadb) 
        brew uninstall mysql (mariadb)
        brew cleanup

        sudo rm -rf /usr/local/var/mysql
        sudo rm -rf /usr/local/bin/mysql*
        sudo rm -rf /usr/local/Cellar/mysql --/mariadb
        sudo rm -rf /usr/local/var/homebrew/{mac-user}/mariadb
        sudo rm -rf /usr/local/etc/my.cnf 
        sudo rm -rf /usr/local/etc/my.cnf.d --설정파일

        sudo mysql -uroot
        use mysql;
        set password for root@'localhost' = PASSWORD('12345');

    
    nslookup naver.com과 nslookup google.com을 사용하여 나온 결과값 '223.130.195.200', '142.250.207.46'을
    InetAddress.getByName(아이피 주소).isReachable(1000)에 대입하여 나오는 결과값은 false이고 현재 개인적으로 사용하고 있는 AWS EC2 서버 아이피를 사용하면
    true 결과값이 나옴