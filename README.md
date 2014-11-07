<h1>SimpleJsonParser</h1>

<p>A simple json parser implements by using scala parsers.</p>

<p>{"store":{"bicycle":{"price":19.951,"color":"red1"},"fruit":[{"weight":8,"type":"apple"},{"weight":9,"type":"pear"}]},"email":"amy@only<em>for</em>json<em>udf</em>test.net","owner":"amy1"}</p>

<p>RESULT</p>

<p>{
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
    "email":"amy@only<em>for</em>json<em>udf</em>test.net",
    "owner":"amy1"
}</p>

