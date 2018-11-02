create sequence seq_grupo start with 1 increment by 1;
create table grupo (id bigint not null, data_cadastro timestamp not null, nome varchar(60) not null, primary key (id));
