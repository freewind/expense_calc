Travelling Allowance Calculator for TWer
========================================

Make the travelling allowance calculation easier!

## Install & Run

1. Install Java
2. `git clone https://github.com/freewind/expense_calc.git`
3. `cd expense_calc`
4. `./sbt run`

## Output

```
Input start date(yyyy-MM-dd, or MM-dd if it's in current year):
03-09
Input end date(MM-dd or yyyy-MM-dd, use today if empty):

Input allowance for weekday(¥150 if empty):

Input allowance for weekend(¥350 if empty):

########################################
2014-03-09 ~ 2014-12-01

total days: 268
weekday count: 191, weekend day count: 77

allowance for weekday: 150, weekend: 350
Total allowance: 55600

2014-03-09 ~ 2014-12-01, 268 days, 150*191+350*77=55600
########################################
```
