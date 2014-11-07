<h1>SimpleJsonParser</h1>

<p>A simple json parser implements by using scala parsers.</p>

一个简单的Json解析代码，使用Scala编写。目前只能解析日期（2014-11-07 19:19:00格式）、字符串、boolean值、数组、数字、对象等。

    {"store":{"bicycle":{"price":19.951,"color":"red1"},"fruit":[{"weight":8,"type":"apple"},{"weight":9,"type":"pear"}]},"email":"amy@only<em>for</em>json<em>udf</em>test.net","owner":"amy1"}

<p>Result</p>

    {
        "store":{
            "bicycle":{
                "price":19.951,
                "color":"red1"
            },
            "fruit":[
                {
                    "weight":8.0,
                    "type":"apple"
                },
                {
                    "weight":9.0,
                    "type":"pear"
                }
            ]
        },
        "blog":"http://www.iteblog.com",
        "owner":"iteblog"
    }

```json
  var ihubo = {
    nickName  : "草依山",
    site : "http://jser.me"
  }
```
