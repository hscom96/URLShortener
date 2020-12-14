<h1 align="center" style="color:red"> URL Shortener :rocket: </h1>

</br></br>

## :hourglass: Dev Preiod 
- 2020.12.07 ~ 11

</br>

## 🧐 What is 'URL Shortener' Service? 
- URL 입력 폼 제공
- 단축 후 결과 출력
- 동일한 URL을 입력 할 경우 항상 동일한 shortening 결과 값이 나와야 함
- shortening 의 결과 값은 8문자 이내로 생성
- 브라우저에서 shortening URL을 입력하면 원래 URL로 리다이렉트


</br>

## 👨‍ Part
총 1인
-  김현수 : 프론트, 백엔드 


</br>

## :closed_book: 기술

#### :orange_book: backend
- Springboot
- MYSQL 

#### :orange_book: frontend
- HTML
- CSS
- JavaScript

</br>

## :package: 개발 환경
- jdk 11
- mysql 8.0.22

</br>

## 📸 ScreenShots
#### :heavy_check_mark: 메인
<p align="center">

![image](https://user-images.githubusercontent.com/46397442/102028558-3378d600-3dee-11eb-8e19-ecfded171d36.png)

</p>

#### :heavy_check_mark: 단축완료
<p align="center">

![image](https://user-images.githubusercontent.com/46397442/102028569-425f8880-3dee-11eb-81a5-74c65f04d6d7.png)

</p>

## Rest API

- URL 단축 API 
1) POST 
2) Request url
http:localhost:8030/short
3) Request Body example (JSON):
```json
{ 
"originURL" : "https://stackoverflow.com/questions/45739517/jpa-repository-lob-column"
}
```
4) Response example (JSON) :
```json
{
    "originURL": "https://stackoverflow.com/questions/45739517/jpa-repository-lob-column",
    "shortURL": "http://localhost:8030/short/c",
    "count": 5
}
```

- 단축URL Redirect
1) GET 
2) Request url
http://localhost:8030/short/{shortURL}
