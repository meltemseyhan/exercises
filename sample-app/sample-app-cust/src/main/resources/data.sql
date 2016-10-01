INSERT INTO `user`
(
`email`,
`enabled`,
`expired`,
`first_name`,
`last_name`,
`locked`,
`password`,
`username`)
VALUES
(
'meltem@yesiltas.net',
 1,
 0,
'Meltem',
'Yesiltas',
 0,
'password',
'meltem');

INSERT INTO `customer`
(
`address`,
`city`,
`country`,
`email`,
`name`)
VALUES
(
'Addresss',
'Istanbul',
'Turkey',
'meltem@yesiltas.net',
'Meltem Yesiltas');