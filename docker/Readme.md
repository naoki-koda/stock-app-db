# イメージのビルド
$ Docker-Compose build

# コンテナの作成
$ Docker-Compose up -d

# イメージの確認
$ docker images

# コンテナの起動
$ docker start <IMAGE ID>

# 起動したコンテナにログイン
$ docker exec -it Docker-mysql_mysql_1 bash -p

# MySQLを起動
$ mysql -u root -p -h 127.0.0.1

# この後パスワードを入力して完了