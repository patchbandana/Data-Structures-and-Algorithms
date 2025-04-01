create schema worldtemps;

use WorldTemps;

create table temps (
Region varchar(50),
Country varchar(50),
State varchar(25),
City varchar(25),
Month integer,
Day integer,
Year integer,
AvgTemperature float);
