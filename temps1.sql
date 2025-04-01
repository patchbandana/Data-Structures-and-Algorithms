SELECT * FROM worldtemps.temps;

select distinct(Year) from temps;

select * from temps where year = 201;

select count(avgtemperature) from temps where avgtemperature = -99;

delete from temps where avgtemperature = -99;