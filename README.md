# oauth2-authserver-java
:star: Java OAuth2 Auth Server, SpringBoot, Gradle

2017-11-11: This repo has already dockerized :open_hands: 

## Setup

1. Install Docker On macOS: [docker-for-mac](https://docs.docker.com/docker-for-mac/install/)
2. `git clone git@github.com:universe-white-chief/oauth2-authserver-java.git`
3. `cd` project root path  
  3.1. 如果你本地有`gradle-3.4.1-bin.zip`压缩包, 则在该项目运行时不需要另外下载. 请将`gradle-3.4.1-bin.zip`拷贝到`oauth2-authserver-java/gradle/wrapper/`目录下   
  3.2. 修改`gradle-wrapper.properties`文件的`distributionUrl=https\://services.gradle.org/distributions/gradle-3.4.1-bin.zip`为`distributionUrl=gradle-3.4.1-bin.zip`  
4. run as Detached mode on local env: `docker-compose up -d`

## Usage
 
1. **获取token-info**  

POST /oauth/token  

Headers:  
Authorization: Basic base64(clientId:clientSecret)  

```bash
curl -i -X POST \
   -H "Content-Type:application/x-www-form-urlencoded" \
   -H "Authorization:Basic c2RkdGM6c2RkdGNzZWNyZXQ=" \
   -d "username=sddtc" \
   -d "password=sddtc" \
   -d "grant_type=password" \
 'http://localhost:9010/oauth/token'
```

返回值:  
token-info:  

```
{
  "access_token": "07af0a39-2279-4ad2-83be-41725aef2033",
  "token_type": "bearer",
  "refresh_token": "1e51eefa-efbb-4d0b-9ec8-d946a334313b",
  "expires_in": 599,
  "scope": "read report"
}

```

2. **获取用户信息**  
  
GET /oauthuser  

Headers:  
Authorization: bearer {access_token}  

```bash
curl -i -X GET \
   -H "Authorization:bearer 4ee1510d-eca1-49f4-bd28-f45d6069618c" \
 'http://localhost:9010/oauthuser'
```

返回值:  
user-info:  

```
{
  "authorities": [
    {
      "authority": "Role_Admin"
    },
    {
      "authority": "Role_CRNAN"
    },
    {
      "authority": "Role_Category"
    },
    {
      "authority": "Role_Reference"
    }
  ]
  ...
 }

```

3. **刷新token**
  
POST /oauth/token  

Headers:  
Authorization: Basic base64(clientId:clientSecret)

Body:  
grant_type: refresh_token  
refresh_token: {refresh_token}  

```bash
curl -i -X POST \
   -H "Content-Type:application/x-www-form-urlencoded" \
   -H "Authorization:Basic c2RkdGM6c2RkdGNzZWNyZXQ=" \
   -d "grant_type=refresh_token" \
   -d "refresh_token=570a56a5-92e7-4a8c-9aac-701ad3e0b41b" \
 'http://localhost:9010/oauth/token'
```

返回值:  
token-info:  

```

{
  "access_token": "67560f76-5505-471a-9b85-8e9ff02fb181",
  "token_type": "bearer",
  "refresh_token": "1e51eefa-efbb-4d0b-9ec8-d946a334313b",
  "expires_in": 599,
  "scope": "read report"
}

```

4. **强制过期token**  
   
GET /revoketoken  

Headers  
Authorization: bearer {access_token}  

```bash
curl -i -X GET \
   -H "Authorization:bearer fc63fd82-3bce-4afe-a7bc-1e15392defe4" \
 'http://localhost:9010/revoketoken'
```

返回值:  
success  


5. **访问资源服务器**

(useless)    

GET /data  

Headers  
Authorization: bearer {access_token}  

"# oauth2-authserver-java-master" 
