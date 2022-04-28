/*
Este procedimento permite a inserção de um tipo de utilizador.
*/
DROP PROCEDURE IF EXISTS sp_inserirTipoUtilizador;
DELIMITER $$
CREATE PROCEDURE sp_inserirTipoUtilizador(IN designacao varchar(15))
BEGIN
INSERT INTO tipoUtilizador VALUES(designacao);
END $$ DELIMITER ;


/*
Este procedimento permite a inserção de um novo utilizador.
*/
DROP PROCEDURE IF EXISTS sp_inserirUtilizador;
DELIMITER $$
CREATE PROCEDURE sp_inserirUtilizador(IN username varchar(255),IN pswrd varchar(30),IN email varchar(255),IN data_nascimento date, IN designacao varchar(15))
BEGIN
INSERT INTO utilizador VALUES(username,pswrd,email,data_nascimento, designacao);
END $$ DELIMITER ;

/*
Este procedimento permite a inserção de um novo evento.
*/
DROP PROCEDURE IF EXISTS sp_inserirEvento;
DELIMITER $$
CREATE PROCEDURE sp_inserirEvento(IN designacao varchar(200),IN hora_inicio timestamp,IN hora_fim timestamp)
BEGIN
INSERT INTO evento(ev_designacao, ev_hora_inicio, ev_hora_fim) VALUES(designacao, hora_inicio, hora_fim);
END $$ DELIMITER ;

/*
Este procedimento permite a inserção de um novo evento.
*/
DROP PROCEDURE IF EXISTS sp_inserirUtilizadorEvento;
DELIMITER $$
CREATE PROCEDURE sp_inserirUtilizadorEvento(IN username varchar(200), IN ev_id int, IN inscricao int)
BEGIN
INSERT INTO utilizador_evento(utEv_ut_username, utEv_ev_id, utEv_inscricao) VALUES(username, ev_id, inscricao);
END $$ DELIMITER ;

/*
Este procedimento permite a inserção de uma nova sessao.
*/
DROP PROCEDURE IF EXISTS sp_inserirSessao;
DELIMITER $$
CREATE PROCEDURE sp_inserirSessao(IN designacao varchar(200),IN hora_inicio timestamp,IN hora_fim timestamp, IN duracao int, IN ses_ev_id int)
BEGIN
INSERT INTO sessao(ses_designacao, ses_hora_inicio, ses_hora_fim, ses_duracao, ses_ev_id) VALUES(designacao,hora_inicio,hora_fim,duracao, ses_ev_id);
END $$ DELIMITER ;

/*
Este procedimento permite a inserção de um novo utilizador sessao.
*/
DROP PROCEDURE IF EXISTS sp_inserirUtilizadorSessao;
DELIMITER $$
CREATE PROCEDURE sp_inserirUtilizadorSessao(IN username varchar(200), IN ses_id int, IN inscricao int)
BEGIN
INSERT INTO utilizador_sessao  VALUES (username, ses_id, inscricao);
END $$ DELIMITER ;

/*
Este procedimento permite a inserção de um novo pack.
*/
DROP PROCEDURE IF EXISTS sp_inserirPack;
DELIMITER $$
CREATE PROCEDURE sp_inserirPack(IN designacao varchar(10),IN descricao varchar(250),IN preco int, IN quantidade int)
BEGIN
INSERT INTO pack VALUES(designacao,descricao,preco,quantidade);
END $$ DELIMITER ;

/*
Este procedimento permite a inserção de uma nova sessao pack.
*/
DROP PROCEDURE IF EXISTS sp_inserirSessaoPack;
DELIMITER $$
CREATE PROCEDURE sp_inserirSessaoPack(IN ses_id int, IN pack_designacao varchar(200))
BEGIN
INSERT INTO sessao_pack VALUES(ses_id, pack_designacao);
END $$ DELIMITER ;

/*
Este procedimento permite a inserção de um novo equipamento.
*/
DROP PROCEDURE IF EXISTS sp_inserirEquipamento;
DELIMITER $$
CREATE PROCEDURE sp_inserirEquipamento(IN codigo int, IN designacao varchar(200),IN preco int,IN quantidade int)
BEGIN
INSERT INTO equipamento VALUES(codigo,designacao,preco,quantidade);
END $$ DELIMITER ;

/*
Este procedimento permite a inserção de uma nova sala.
*/
DROP PROCEDURE IF EXISTS sp_inserirSala;
DELIMITER $$
CREATE PROCEDURE sp_inserirSala(IN numero int,IN descricao varchar(250),IN capacidade int)
BEGIN
INSERT INTO sala VALUES(numero,descricao,capacidade);
END $$ DELIMITER ;

/*
Este procedimento permite a inserção de uma nova sessao sala.
*/
DROP PROCEDURE IF EXISTS sp_inserirSessaoSala;
DELIMITER $$
CREATE PROCEDURE sp_inserirSessaoSala(IN ses_id int,IN sala_numero int)
BEGIN
INSERT INTO sessao_sala VALUES(ses_id,sala_numero);
END $$ DELIMITER ;

/*
Este procedimento permite a inserção de uma nova avaliacao.
*/
DROP PROCEDURE IF EXISTS sp_inserirAvaliacao;
DELIMITER $$
CREATE PROCEDURE sp_inserirAvaliacao(IN codigo int,IN classificacao int,IN comentario varchar(250),IN dta datetime)
BEGIN
INSERT INTO avaliacao VALUES(codigo,classificacao,comentario, dta);
END $$ DELIMITER ;

/*
Este procedimento permite a inserção de uma nova sessao avaliacao.
*/
DROP PROCEDURE IF EXISTS sp_inserirSessaoAvaliacao;
DELIMITER $$
CREATE PROCEDURE sp_inserirSessaoAvaliacao(IN ses_id int,IN aval_cod int)
BEGIN
INSERT INTO sessao_avaliacao VALUES(ses_id,aval_cod);
END $$ DELIMITER ;

/*
Este procedimento permite a inserção de um novo utilizador avaliacao.
*/
DROP PROCEDURE IF EXISTS sp_inserirUtilizadorAvaliacao;
DELIMITER $$
CREATE PROCEDURE sp_inserirUtilizadorAvaliacao(IN ut_usern varchar(200),IN aval_cod int)
BEGIN
INSERT INTO utilizador_avaliacao VALUES(ut_usern,aval_cod);
END $$ DELIMITER ;

/*
Este procedimento permite a inserção de um novo suporte sessao.
*/
DROP PROCEDURE IF EXISTS sp_inserirSuporteSessao;
DELIMITER $$
CREATE PROCEDURE sp_inserirSuporteSessao(IN numeroEquipamentos int)
BEGIN
INSERT INTO suporte_sessao (sp_numeroEquipamentos) VALUES(numeroEquipamentos);
END $$ DELIMITER ;

/*
Este procedimento permite a inserção de um novo equipamento sessao.
*/
DROP PROCEDURE IF EXISTS sp_inserirEquipamentoSessao;
DELIMITER $$
CREATE PROCEDURE sp_inserirEquipamentoSessao(IN equi_codigo int,IN sp_id int)
BEGIN
INSERT INTO equipamento_sessao VALUES(equi_codigo,sp_id);
END $$ DELIMITER ;

/*
Este procedimento permite a inserção de uma nova localidade.
*/
DROP PROCEDURE IF EXISTS sp_inserirLocalidade;
DELIMITER $$
CREATE PROCEDURE sp_inserirLocalidade(IN morada varchar(100))
BEGIN
INSERT INTO localizacao(lcl_morada) VALUES(morada);
END $$ DELIMITER ;

/*
Este procedimento permite a inserção de um novo evento local.
*/
DROP PROCEDURE IF EXISTS sp_inserirEventoLocalidade;
DELIMITER $$
CREATE PROCEDURE sp_inserirEventoLocalidade(IN ev_id int, IN lcl_id int)
BEGIN
INSERT INTO evento_localizacao VALUES(ev_id, lcl_id);
END $$ DELIMITER ;

/*
Este procedimento permite a inserção de um novo spses sessao.
*/
DROP PROCEDURE IF EXISTS sp_inserirSpSSessao;
DELIMITER $$
CREATE PROCEDURE sp_inserirSpSSessao(IN spSesSes_sesSup_id int,IN spSesSes_ses_id int,IN spSes_ses_designacao varchar(200),IN spSes_ses_hora_inicio timestamp,IN spSes_ses_hora_fim timestamp ,IN spSes_ses_duracao int,IN spSes_ses_ev_id int)
BEGIN
INSERT INTO suporteSessao_sessao VALUES(spSesSes_sesSup_id, spSesSes_ses_id, spSes_ses_designacao, spSes_ses_hora_inicio, spSes_ses_hora_fim, spSes_ses_duracao, spSes_ses_ev_id);
END $$ DELIMITER ;

#-------------------------

/*Q1 Lista de Locais nunca usados em eventos;*/
SELECT lcl_id as 'Identificador Localização', lcl_morada as 'Morada'
FROM localizacao
WHERE lcl_id NOT IN (SELECT lcl_id
                FROM localizacao JOIN evento JOIN evento_localizacao
                ON evLcl_lcl_id = lcl_id AND evLcl_ev_id = ev_id);

/*Q2 Lista de utilizadores VIP que participaram nalgum evento e respetivas sessões;*/
SELECT DISTINCT ut_username as 'Nome do Utilizador', utSes_ses_id as 'Identificador Sessão'
FROM utilizador, tipoUtilizador, utilizador_evento, utilizador_sessao
WHERE ut_username = utEv_ut_username AND ut_username = utSes_ut_username AND utEv_ut_username = utSes_ut_username AND ut_tpUt_designacao = tpUt_designacao AND tpUt_designacao = 'VIP';

/*Q3 Lista dos participantes que não estão inscritos em nenhum evento;*/
SELECT ut_username as 'Nome do Utilizador'
FROM utilizador, utilizador_evento
WHERE utEv_ev_id NOT IN (SELECT ev_id
                    FROM evento, utilizador_evento 
                    WHERE ev_id = utEv_ev_id);

/*
P3 - Listagem do número utilizadores por tipo (de utilizador).
*/
DROP PROCEDURE IF EXISTS sp_numero_utilizadores;
DELIMITER $$
CREATE PROCEDURE sp_numero_utilizadores()
BEGIN
SELECT tpUt_designacao as 'Tipo de Utilizador', count(ut_username) as 'Count' from utilizador u , tipoUtilizador tp
where u.ut_tpUt_designacao = tp.tpUt_designacao
group by tpUt_designacao;
END $$ DELIMITER ;

/*
P4 - Lista de elementos adicionais a usar por sessão de um evento e respetivo preço.
*/
DROP PROCEDURE IF EXISTS sp_item_por_sessao;
DELIMITER $$
CREATE PROCEDURE sp_item_por_sessao()
BEGIN
SELECT ses_id, equi_designacao, equi_preco from sessao s, equipamento e, suporte_sessao sp, equipamento_sessao es, suporteSessao_sessao ssp
where e.equi_codigo = es.equiSes_equi_codigo and s.ses_id = ssp.spSesSes_ses_id and sp.sp_id = es.equiSes_sp_id and sp.sp_id = ssp.spSesSes_sesSup_id;
END $$ DELIMITER ;

/*
P6 - Devolve na primeira linha o nome do evento e a sua avaliação média
*/
DROP PROCEDURE IF EXISTS sp_MediaAvaliacoes;
DELIMITER $$
CREATE PROCEDURE sp_MediaAvaliacoes()
BEGIN
SELECT ev_designacao, avg(avl_classificacao) from evento e , avaliacao a, sessao_avaliacao sa, sessao s
where a.avl_codigo = sa.sesAvl_avl_codigo and s.ses_ev_id = e.ev_id and s.ses_ev_id = sa.sesAvl_ses_id
group by ev_designacao;
END $$ DELIMITER ;

/*
P8 - Permitir consultar todos os comentários e avaliações associados a uma sessão.
*/
DROP PROCEDURE IF EXISTS sp_comentarios;
DELIMITER $$
CREATE PROCEDURE sp_comentarios()
BEGIN
SELECT ses_id as 'ID Sessao', avl_comentario as 'Comentário' , avl_classificacao as 'Avaliação (0-5)' from avaliacao a, sessao s, sessao_avaliacao sa
where sa.sesAvl_ses_id = s.ses_id and a.avl_codigo = sa.sesAvl_avl_codigo;
END $$ DELIMITER ;


/*
P7 - Permitir consultar a informação completa de cada comunicação/sessão.
*/
DROP PROCEDURE IF EXISTS sp_sessoes;
DELIMITER $$
CREATE PROCEDURE sp_sessoes()
BEGIN
SELECT ses_id as 'ID Sessao', ses_designacao as'Designação da Sessão', ses_hora_inicio as 'Hora de Inicio',ses_hora_fim as 'Hora de Fim' from sessao
group by ses_id;
END $$ DELIMITER ;


/*
Este procedimento permite consultar todos so comentários e avaliações associados a uma sessão/comunicação, com a identificação do utilizador.
*/
DROP PROCEDURE IF EXISTS sp_sessoes;
DELIMITER $$
CREATE PROCEDURE sp_sessoes(id int)
BEGIN
SELECT a.*, u.Username from utilizador_avaliacao ua INNER JOIN vAvaliacoes a INNER JOIN vUtilizadores u ON ua.utAvl_ut_username = u.Username WHERE  ua.utAvl_ut_username = id;
END $$ DELIMITER ;

/*
Esta consulta apresenta as características de todos os utilizadores.
*/
CREATE OR REPLACE VIEW vUtilizadores(Username,Psswrd,Email, Data_Nascimento,Tipo) 
AS SELECT * from utilizador;

/*
Esta consulta apresenta os horários de todos os eventos.
*/
CREATE OR REPLACE VIEW vHorariosEventos(ID, Designação ,Data_Hora_Inicial,Data_Hora_Final) 
AS SELECT * from evento;

/*
Esta consulta apresenta as características de todas as avaliações.
*/
CREATE OR REPLACE VIEW vAvaliacoes(Código,Classificação,Comentário,Data) 
AS SELECT * FROM avaliacao;


call sp_numero_utilizadores();
call sp_item_por_sessao();
call sp_MediaAvaliacoes();
call sp_comentarios();
call sp_sessoes(1);

SELECT * FROM vUtilizadores;
SELECT * FROM vAvaliacoes;
SELECT * FROM vHorariosEventos;