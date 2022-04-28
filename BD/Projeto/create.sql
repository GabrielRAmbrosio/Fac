drop database if exists bd;
create database if not exists bd;
use bd;

create table tipoUtilizador(
	tpUt_designacao varchar(15) not null,
    primary key(tpUt_designacao)
);

create table utilizador (
    ut_username varchar(200) not null,
    ut_password varchar(20) not null,
    ut_email varchar(30) not null,
    ut_data_nascimento date not null,
    ut_tpUt_designacao varchar(15) not null,
    primary key (ut_username),
    foreign key (ut_tpUt_designacao) references tipoUtilizador(tpUt_designacao)
);

create table evento (
    ev_id int not null auto_increment,
    ev_designacao varchar(200) not null,
    ev_hora_inicio timestamp not null,
    ev_hora_fim timestamp not null,
    primary key (ev_id)
);

create table utilizador_evento(
	utEv_ut_username varchar(200) not null,
    utEv_ev_id int not null,
    utEv_inscricao int not null,
    primary key(utEv_ut_username, utEv_ev_id),
    foreign key(utEv_ut_username) references utilizador(ut_username),
    foreign key(utEv_ev_id) references evento(ev_id)
);

create table sessao (
	ses_id int not null auto_increment,
    ses_designacao varchar(200) not null,
    ses_hora_inicio timestamp not null,
    ses_hora_fim timestamp not null,
    ses_duracao int not null,
    ses_ev_id int not null,
    primary key (ses_id),
    foreign key(ses_ev_id) references evento(ev_id)
);

create table utilizador_sessao(
	utSes_ut_username varchar(200) not null,
    utSes_ses_id int not null,
    utSes_inscricao int not null,
    primary key(utSes_ut_username, utSes_ses_id),
    foreign key(utSes_ut_username) references utilizador(ut_username),
    foreign key(utSes_ses_id) references sessao(ses_id)
);

create table pack (
    pk_designacao varchar(10) not null,
    pk_descricao varchar(250) not null,
    pk_preco int not null,
    pk_quantidade int not null,
    primary key (pk_designacao)
);

create table sessao_pack(
	sesPc_ses_id int not null auto_increment,
    sesPc_pack_designacao varchar(200) not null,
    primary key (sesPc_ses_id),
    foreign key(sesPc_ses_id) references sessao(ses_id),
    foreign key(sesPc_pack_designacao) references pack(pk_designacao)
);

create table equipamento (
	equi_codigo int not null,
    equi_designacao varchar(100) not null,
    equi_preco int not null,
    equi_quantidade int not null,
    primary key (equi_codigo)
);

create table sala (
    sala_numero int not null,
    sala_descricao varchar(250) not null,
    sala_capacidade int not null,
    primary key (sala_numero)
);

create table sessao_sala(
	sesSala_ses_id int not null auto_increment,
    sesSala_sala_numero int not null,
    primary key(sesSala_ses_id),
    foreign key(sesSala_ses_id) references sessao(ses_id),
    foreign key(sesSala_sala_numero) references sala(sala_numero)
);

create table avaliacao (
	avl_codigo int not null,
    avl_classificacao int not null,
    avl_comentario varchar(250) not null,
    avl_data datetime not null,
    primary key (avl_codigo)
);

create table sessao_avaliacao(
	sesAvl_ses_id int not null auto_increment,
    sesAvl_avl_codigo int not null,
    primary key(sesAvl_avl_codigo),
    foreign key(sesAvl_ses_id) references sessao(ses_id),
    foreign key(sesAvl_avl_codigo) references avaliacao(avl_codigo)
);

create table utilizador_avaliacao (
	utAvl_ut_username varchar(200) not null,
    utAvl_avl_codigo int not null,
    primary key(utAvl_avl_codigo),
    foreign key(utAvl_ut_username) references utilizador(ut_username),
    foreign key(utAvl_avl_codigo) references avaliacao(avl_codigo)
);

create table suporte_sessao (
	sp_id int not null auto_increment,
    sp_numeroEquipamentos int,
    primary key (sp_id)
);

create table equipamento_sessao(
	equiSes_equi_codigo int not null,
    equiSes_sp_id int not null auto_increment,
    primary key(equiSes_equi_codigo,equiSes_sp_id),
    foreign key(equiSes_equi_codigo) references equipamento(equi_codigo),
    foreign key(equiSes_sp_id) references suporte_sessao(sp_id)
);

create table localizacao (
	lcl_id int not null auto_increment,
    lcl_morada varchar(100),
    primary key (lcl_id)
);

create table evento_localizacao(
	evLcl_ev_id int not null,
    evLcl_lcl_id int not null,
    primary key(evLcl_ev_id),
    foreign key(evLcl_ev_id) references evento(ev_id),
    foreign key(evLcl_lcl_id) references localizacao(lcl_id)
);

create table suporteSessao_sessao(
	spSesSes_sesSup_id int not null,
    spSesSes_ses_id int not null,
    spSes_ses_designacao varchar(200) not null,
    spSes_ses_hora_inicio timestamp not null,
    spSes_ses_hora_fim timestamp not null,
    spSes_ses_duracao int not null,
    spSes_ses_ev_id int not null,
    primary key(spSesSes_ses_id),
    foreign key(spSesSes_sesSup_id) references suporte_sessao(sp_id),
    foreign key(spSes_ses_ev_id) references evento(ev_id)
);