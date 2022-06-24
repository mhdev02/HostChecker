# HostChecker

## 개발 아이디어 

CommandLineRunner 인터페이스를 이용하여 스프링 부트 앱 구동 시 등록된 호스트 IP를 이용해서 
InetAddress.isReachable() 결과값을 데이터베이스에 업데이트. 이때, 동시성(멀티 쓰레딩)을 활용

## REST API 설명

    GET /hosts/{ip} 

        주어진 ip의 alive 상태 확인 

    GET /hosts

        등록된 모든 호스트의 alive 상태 확인

    POST /hosts { "name": "" , "ip": "" }

        호스트 등록

    PUT /hosts { "name": , "ip" }

        호스트 정보 수정

    DELETE /hosts/{ip} { "name": , "ip" }

        ip를 이용해 해당 호스트 삭제

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
    InetAddress.getByName()에 대입하여 나오는 결과값은 false이고 현재 개인적으로 사용하고 있는 AWS EC2 서버 아이피를 사용하면
    true 결과값이 나옴