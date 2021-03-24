## world | GET |
OUTPUT "Hello World";

-- mysql.qross=
-- jdbc.default=
## example | GET |
OPEN mysql.qross;
CACHE 'temp' # SELECT * FROM qross_conf;

OPEN CACHE;
SELECT * FROM temp;