SimpleJsonParser
================

 A simple json parser implements by using scala parsers.



{"store":{"bicycle":{"price":19.951,"color":"red1"},"fruit":[{"weight":8,"type":"apple"},{"weight":9,"type":"pear"}]},"email":"amy@only_for_json_udf_test.net","owner":"amy1"}

RESULT

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
	"email":"amy@only_for_json_udf_test.net",
	"owner":"amy1"
}
