create table `user_subscriptions` (
  channel_id integer not null,
  subscriber_id integer not null,
  primary key (channel_id, subscriber_id)
)
  engine=MyISAM;

alter table `user_subscriptions` add constraint channel_user_fk foreign key (channel_id) references `user` (id);
alter table `user_subscriptions` add constraint subscriber_user_fk foreign key (subscriber_id) references `user` (id);