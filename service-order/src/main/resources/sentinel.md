### 架构
    6379 主
    6380、6381 从
    26379、26380、26381 哨兵

### 启动主从：
    redis-server 6379.conf
    redis-server 6380.conf --replicaof 127.0.0.1 6379
    redis-server 6381.conf --replicaof 127.0.0.1 6379

### 启动哨兵：
    redis-server 26379.conf --sentinel
    redis-server 26380.conf --sentinel
    redis-server 26381.conf --sentinel

### 注意项：
#### 1、关闭防火墙
#### 2、注释 bind 127.0.0.1
   不能被除了本机以外的ip地址访问，默认是本地127.0.0.1
#### 3、关闭保护模式
   protected mode没有设置为no，保护模式没有关闭，默认是yes
#### 4、哨兵配置
    26379.conf:
    port 26379
    daemonize yes
    sentinel monitor mymaster 192.168.244.8 6379 2
    
    26380.conf:
    port 26380
    daemonize yes
    sentinel monitor mymaster 192.168.244.8 6379 2
    
    26381.conf:
    port 26381
    daemonize yes
    sentinel monitor mymaster 192.168.244.8 6379 2