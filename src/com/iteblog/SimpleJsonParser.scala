package com.iteblog

import scala.util.parsing.combinator.JavaTokenParsers

/**
 * User: iteblog
 * webSite: www.iteblog.com
 * Date: 2014/11/7
 * Time: 15:51
 *
 * A simple json parser
 */
class SimpleJsonParser extends JavaTokenParsers {
  def NUM: Parser[Float] = floatingPointNumber ^^ (_.toFloat)

  def BOOLEAN: Parser[Boolean] = "(true|false)".r ^^ (_.toBoolean)

  def DATE: Parser[String] = "(\\d{4}-\\d{2}-\\d{2}\\s\\d{2}:\\d{2}:\\d{2})".r ^^ (_.toString)

  def STRING: Parser[String] = stringLiteral | ident ^^ (_.toString)

  def NULL: Parser[Null] = "(null|NULL)".r ^^ (_ => null)

  def TERM = NUM | BOOLEAN | STRING | NULL | ARRAY | OBJECT | DATE

  def ARRAY: Parser[List[Any]] = "[" ~> rep(TERM <~ ",?".r) <~ "]" ^^ (l => l)

  def OBJECT: Parser[Map[String, Any]] = "{" ~> rep(((ident | STRING) ~ ":" ~ DATE |
    (ident | STRING) ~ ":" ~ NUM |
    (ident | STRING) ~ ":" ~ BOOLEAN |
    (ident | STRING) ~ ":" ~ STRING |
    (ident | STRING) ~ ":" ~ NULL |
    (ident | STRING) ~ ":" ~ ARRAY |
    (ident | STRING) ~ ":" ~ OBJECT) <~ ",?".r) <~ "}" ^^ {
    pairs =>
      var map = Map[String, Any]()
      pairs.foreach {
        case k ~ ":" ~ v => map = map ++ Map(k -> v)
      }
      map
  }

  def printMap(data: Map[String, Any], dep: Int): StringBuilder = {
    val buff = new StringBuilder
    if (data.size == 0) {
      buff.append("{},\n")
      buff
    } else {
      buff.append("{\n")
      data.foreach {
        case (k, v) => v match {
          case v: Map[String, List[Any]] =>
            buff.append("\t" * (dep + 1) + k + ":")
            buff.append(printMap(v, dep + 1))
          case v: List[Any] =>
            buff.append("\t" * (dep + 1) + k + ":[\n")
            buff.append(printList(v, dep + 2));
            buff.append("\t" * (dep + 1) + "],\n")
          case _ => buff.append("\t" * (dep + 1) + k + ":" + v + ",\n")
        }
      }
      buff.delete(buff.size - 2, buff.size)
      buff.append("\n")
      buff.append("\t" * dep + "},\n")
      buff
    }
  }

  def printList(data: List[Any], dep: Int): StringBuilder = {
    val buff = new StringBuilder
    data.foreach {
      case map: Map[String, List[Any]] =>
        buff.append("\t" * dep)
        buff.append(printMap(map, dep))
      case list: List[Any] => buff.append(printList(list, dep + 1))
      case elem => buff.append("\t" * dep + elem + ",\n")
    }
    if (buff.size >= 2) {
      buff.delete(buff.size - 2, buff.size)
      buff.append("\n")
    }
    buff
  }
}

object SimpleJsonParser {
  def main(args: Array[String]) {
    val simpleJsonParser = new SimpleJsonParser
    val result = simpleJsonParser.parseAll(simpleJsonParser.OBJECT,
      """{"store":{"bicycle":
        |{"price":19.951,"color":"red1"},
        |"fruit":[{"weight":8,"type":"apple"},{"weight":9,"type":"pear"}]},
        |"email":"amy@only_for_json_udf_test.net",
        |"owner":"amy1"}""".stripMargin)
    val buff = simpleJsonParser.printMap(result.get, 0)
    buff.delete(buff.size - 2, buff.size)
    buff.append("\n")
    print(buff)
  }
}