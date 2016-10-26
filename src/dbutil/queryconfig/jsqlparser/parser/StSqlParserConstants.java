package dbutil.queryconfig.jsqlparser.parser;

public interface StSqlParserConstants {

  int EOF = 0;
  int K_AS = 5;
  int K_BY = 6;
  int K_DO = 7;
  int K_IS = 8;
  int K_IN = 9;
  int K_OR = 10;
  int K_ON = 11;
  int K_ALL = 12;
  int K_AND = 13;
  int K_ANY = 14;
  int K_KEY = 15;
  int K_NOT = 16;
  int K_SET = 17;
  int K_ASC = 18;
  int K_TOP = 19;
  int K_END = 20;
  int K_DESC = 21;
  int K_INTO = 22;
  int K_NULL = 23;
  int K_LIKE = 24;
  int K_DROP = 25;
  int K_JOIN = 26;
  int K_LEFT = 27;
  int K_FROM = 28;
  int K_OPEN = 29;
  int K_CASE = 30;
  int K_WHEN = 31;
  int K_THEN = 32;
  int K_ELSE = 33;
  int K_SOME = 34;
  int K_FULL = 35;
  int K_WITH = 36;
  int K_TABLE = 37;
  int K_WHERE = 38;
  int K_USING = 39;
  int K_UNION = 40;
  int K_GROUP = 41;
  int K_BEGIN = 42;
  int K_INDEX = 43;
  int K_INNER = 44;
  int K_LIMIT = 45;
  int K_OUTER = 46;
  int K_ORDER = 47;
  int K_RIGHT = 48;
  int K_DELETE = 49;
  int K_CREATE = 50;
  int K_SELECT = 51;
  int K_OFFSET = 52;
  int K_EXISTS = 53;
  int K_HAVING = 54;
  int K_INSERT = 55;
  int K_UPDATE = 56;
  int K_VALUES = 57;
  int K_ESCAPE = 58;
  int K_PRIMARY = 59;
  int K_NATURAL = 60;
  int K_REPLACE = 61;
  int K_BETWEEN = 62;
  int K_TRUNCATE = 63;
  int K_DISTINCT = 64;
  int K_INTERSECT = 65;
  int K_CONNECT = 66;
  int K_PRIOR = 67;
  int K_START = 68;
  int S_DOUBLE = 69;
  int S_INTEGER = 70;
  int DIGIT = 71;
  int LINE_COMMENT = 72;
  int MULTI_LINE_COMMENT = 73;
  int S_IDENTIFIER = 74;
  int LETTER = 75;
  int SPECIAL_CHARS = 76;
  int S_CHAR_LITERAL = 77;
  int S_QUOTED_IDENTIFIER = 78;

  int DEFAULT = 0;

  String[] tokenImage = {
    "<EOF>",
    "\" \"",
    "\"\\t\"",
    "\"\\r\"",
    "\"\\n\"",
    "\"AS\"",
    "\"BY\"",
    "\"DO\"",
    "\"IS\"",
    "\"IN\"",
    "\"OR\"",
    "\"ON\"",
    "\"ALL\"",
    "\"AND\"",
    "\"ANY\"",
    "\"KEY\"",
    "\"NOT\"",
    "\"SET\"",
    "\"ASC\"",
    "\"TOP\"",
    "\"END\"",
    "\"DESC\"",
    "\"INTO\"",
    "\"NULL\"",
    "\"LIKE\"",
    "\"DROP\"",
    "\"JOIN\"",
    "\"LEFT\"",
    "\"FROM\"",
    "\"OPEN\"",
    "\"CASE\"",
    "\"WHEN\"",
    "\"THEN\"",
    "\"ELSE\"",
    "\"SOME\"",
    "\"FULL\"",
    "\"WITH\"",
    "\"TABLE\"",
    "\"WHERE\"",
    "\"USING\"",
    "\"UNION\"",
    "\"GROUP\"",
    "\"BEGIN\"",
    "\"INDEX\"",
    "\"INNER\"",
    "\"LIMIT\"",
    "\"OUTER\"",
    "\"ORDER\"",
    "\"RIGHT\"",
    "\"DELETE\"",
    "\"CREATE\"",
    "\"SELECT\"",
    "\"OFFSET\"",
    "\"EXISTS\"",
    "\"HAVING\"",
    "\"INSERT\"",
    "\"UPDATE\"",
    "\"VALUES\"",
    "\"ESCAPE\"",
    "\"PRIMARY\"",
    "\"NATURAL\"",
    "\"REPLACE\"",
    "\"BETWEEN\"",
    "\"TRUNCATE\"",
    "\"DISTINCT\"",
    "\"INTERSECT\"",
    "\"CONNECT\"",
    "\"PRIOR\"",
    "\"START\"",
    "<S_DOUBLE>",
    "<S_INTEGER>",
    "<DIGIT>",
    "<LINE_COMMENT>",
    "<MULTI_LINE_COMMENT>",
    "<S_IDENTIFIER>",
    "<LETTER>",
    "<SPECIAL_CHARS>",
    "<S_CHAR_LITERAL>",
    "<S_QUOTED_IDENTIFIER>",
    "\";\"",
    "\"=\"",
    "\",\"",
    "\"(\"",
    "\")\"",
    "\".\"",
    "\"@\"",
    "\"*\"",
    "\"?\"",
    "\">\"",
    "\"<\"",
    "\">=\"",
    "\"<=\"",
    "\"<>\"",
    "\"!=\"",
    "\"@@\"",
    "\"||\"",
    "\"|\"",
    "\"&\"",
    "\"+\"",
    "\"-\"",
    "\"/\"",
    "\"^\"",
    "\"{d\"",
    "\"}\"",
    "\"{t\"",
    "\"{ts\"",
    "\"{fn\"",
  };

}
