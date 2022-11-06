# Gtech-test

## Task
1. [Object Model Customer](https://github.com/PortgasManurung/Gtech-test/blob/main/gtech-server/src/main/java/com/ecommerce/gtech/models/entity/Customer.java)
2. [Schema Relational Database](https://github.com/PortgasManurung/Gtech-test/tree/main/task)

![Schema](https://github.com/PortgasManurung/Gtech-test/blob/main/task/Customer%20relation%20db.PNG)

3. [Forgot Password Service](https://github.com/PortgasManurung/Gtech-test/tree/main/task)

![Service](https://github.com/PortgasManurung/Gtech-test/blob/main/task/task%203.1.PNG)
![Service](https://github.com/PortgasManurung/Gtech-test/blob/main/task/task%203.2.png)
![Service](https://github.com/PortgasManurung/Gtech-test/blob/main/task/task%203.3.PNG)

4. Query List of Customer who are Login in the Last 7 Days
```bash
  SELECT Distinct(u.username) FROM user as u 
  JOIN login_history as lh ON u.id = lh.id_user 
  WHERE lh.login_time >= DATE(NOW() - INTERVAL 7 DAY)
```
