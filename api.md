## 登录

url: `/login`

method: POST

 请求参数：

```json
{
  "account_name": "Edison"
}
```

响应参数：

```json
{
  "success":true,
  "message":""
}
```

## 支出记账

url：`/book-keeping/outcome`

method: POST

 请求参数：
 
```json
{
  "time_stamp": 315105815,
  "amount": 22.00,
  "type": "餐饮",
  "remarks": ""
}
```

 响应参数：
 
```json
{
  "success":true,
  "message":""
}
```

## 收入记账

url：`/book-keeping/income`

method: POST

请求参数：

```json
{
  "time_stamp": 315105815,
  "amount": 22.00,
  "type": "餐饮",
  "remarks": ""
}
```

响应参数：

```json
{
  "success":true,
  "message":""
}
```

## 请求列表消费清单

url:`/detailed-list`

method: GET

 请求参数：

```json
{
  "year": 2021,
  "month": 12
}
```

 响应参数：

```json
{
  "success":true,
  "message":"",
  "time_stamp": 315105815,
  "amount": 22.00,
  "type": "餐饮",
  "remarks": "",
  "is_outcome": true
}
```

## 请求图表消费清单

url:`/detailed-chart`

method: GET

请求参数：

```json
{

}
```

响应参数：

```json
{
  "success":true,
  "message":"",
  "time_stamp": 315105815,
  "amount": 22.00,
  "type": "餐饮",
  "remarks": "",
  "is_outcome": true
}
```

## 删除一次记录

url:`/book-keeping/delete-record`

method: POST

请求参数：

```json
{
  "is_outcome": true,
  "time_stamp": 315105815
}
```

响应参数：

```json
{
  "success":true,
  "message":""
}
```

## 修改一次记录

url:`/book-keeping/modify-record`

method: POST

请求参数：

```json
{
  "is_outcome": true,
  "old_time_stamp": 12412421,
  "time_stamp": 12412421,
  "amount": 22.00,
  "type": "餐饮",
  "remarks": ""
}
```

响应参数：

```json
{
  "success":true,
  "message":""
}
```













