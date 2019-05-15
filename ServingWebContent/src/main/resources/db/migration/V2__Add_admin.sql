INSERT INTO `user` (`id`, `active`, `email`, `password`, `username`)
VALUES ('0', 1, 'admin@admin.com', 'admin', 'admin');

INSERT INTO `user_role` (`user_id`, `roles`)
VALUES ('0', 'USER'), ('0', 'ADMIN');