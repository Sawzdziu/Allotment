--
-- PostgreSQL database dump
--

-- Dumped from database version 10.0
-- Dumped by pg_dump version 10.1

-- Started on 2018-05-09 23:38:38

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 7 (class 2615 OID 39041)
-- Name: rodobronca; Type: SCHEMA; Schema: -; Owner: postgres
--
DROP SCHEMA rodobronca CASCADE ;
CREATE SCHEMA rodobronca;


ALTER SCHEMA rodobronca OWNER TO postgres;

--
-- TOC entry 2896 (class 0 OID 0)
-- Dependencies: 7
-- Name: SCHEMA rodobronca; Type: COMMENT; Schema: -; Owner: postgres
--

COMMENT ON SCHEMA rodobronca IS 'ROD Obronca allotments in Wroclaw.';


SET search_path = rodobronca, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 196 (class 1259 OID 39042)
-- Name: allotment; Type: TABLE; Schema: rodobronca; Owner: postgres
--

CREATE TABLE allotment (
    id_allotment integer NOT NULL,
    bower boolean,
    composter boolean,
    number integer,
    squaremeter integer,
    tree_number integer
);


ALTER TABLE allotment OWNER TO postgres;

--
-- TOC entry 197 (class 1259 OID 39045)
-- Name: allotment_id_allotment_seq; Type: SEQUENCE; Schema: rodobronca; Owner: postgres
--

CREATE SEQUENCE allotment_id_allotment_seq
    AS integer
START WITH 1
INCREMENT BY 1
NO MINVALUE
NO MAXVALUE
CACHE 1;


ALTER TABLE allotment_id_allotment_seq OWNER TO postgres;

--
-- TOC entry 2897 (class 0 OID 0)
-- Dependencies: 197
-- Name: allotment_id_allotment_seq; Type: SEQUENCE OWNED BY; Schema: rodobronca; Owner: postgres
--

ALTER SEQUENCE allotment_id_allotment_seq OWNED BY allotment.id_allotment;


--
-- TOC entry 198 (class 1259 OID 39047)
-- Name: allotment_user_id_allotment_user_seq; Type: SEQUENCE; Schema: rodobronca; Owner: postgres
--

CREATE SEQUENCE allotment_user_id_allotment_user_seq
START WITH 1
INCREMENT BY 1
NO MINVALUE
MAXVALUE 2147483647
CACHE 1;


ALTER TABLE allotment_user_id_allotment_user_seq OWNER TO postgres;

--
-- TOC entry 199 (class 1259 OID 39049)
-- Name: allotment_user; Type: TABLE; Schema: rodobronca; Owner: postgres
--

CREATE TABLE allotment_user (
    id_allotment_user integer DEFAULT nextval('allotment_user_id_allotment_user_seq'::regclass) NOT NULL,
    is_active boolean NOT NULL,
    allotment_id integer NOT NULL,
    user_id integer NOT NULL
);


ALTER TABLE allotment_user OWNER TO postgres;

--
-- TOC entry 200 (class 1259 OID 39053)
-- Name: article; Type: TABLE; Schema: rodobronca; Owner: postgres
--

CREATE TABLE article (
    id_article integer NOT NULL,
    author character varying(255),
    date timestamp with time zone,
    title character varying(255),
    user_id integer NOT NULL,
    text text
);


ALTER TABLE article OWNER TO postgres;

--
-- TOC entry 201 (class 1259 OID 39059)
-- Name: article_id_article_seq; Type: SEQUENCE; Schema: rodobronca; Owner: postgres
--

CREATE SEQUENCE article_id_article_seq
    AS integer
START WITH 1
INCREMENT BY 1
NO MINVALUE
NO MAXVALUE
CACHE 1;


ALTER TABLE article_id_article_seq OWNER TO postgres;

--
-- TOC entry 2898 (class 0 OID 0)
-- Dependencies: 201
-- Name: article_id_article_seq; Type: SEQUENCE OWNED BY; Schema: rodobronca; Owner: postgres
--

ALTER SEQUENCE article_id_article_seq OWNED BY article.id_article;


--
-- TOC entry 202 (class 1259 OID 39061)
-- Name: commentary; Type: TABLE; Schema: rodobronca; Owner: postgres
--

CREATE TABLE commentary (
    id_commentary integer NOT NULL,
    author character varying(255),
    text character varying(500),
    article_id integer NOT NULL,
    user_id integer NOT NULL,
    date timestamp with time zone
);


ALTER TABLE commentary OWNER TO postgres;

--
-- TOC entry 203 (class 1259 OID 39067)
-- Name: commentary_id_commentary_seq; Type: SEQUENCE; Schema: rodobronca; Owner: postgres
--

CREATE SEQUENCE commentary_id_commentary_seq
    AS integer
START WITH 1
INCREMENT BY 1
NO MINVALUE
NO MAXVALUE
CACHE 1;


ALTER TABLE commentary_id_commentary_seq OWNER TO postgres;

--
-- TOC entry 2899 (class 0 OID 0)
-- Dependencies: 203
-- Name: commentary_id_commentary_seq; Type: SEQUENCE OWNED BY; Schema: rodobronca; Owner: postgres
--

ALTER SEQUENCE commentary_id_commentary_seq OWNED BY commentary.id_commentary;


--
-- TOC entry 204 (class 1259 OID 39069)
-- Name: mail; Type: TABLE; Schema: rodobronca; Owner: postgres
--

CREATE TABLE mail (
    id_mail bigint NOT NULL,
    subject character varying(255),
    user_sender_id integer NOT NULL
);


ALTER TABLE mail OWNER TO postgres;

--
-- TOC entry 205 (class 1259 OID 39072)
-- Name: mail_id_mail_seq; Type: SEQUENCE; Schema: rodobronca; Owner: postgres
--

CREATE SEQUENCE mail_id_mail_seq
START WITH 1
INCREMENT BY 1
NO MINVALUE
NO MAXVALUE
CACHE 1;


ALTER TABLE mail_id_mail_seq OWNER TO postgres;

--
-- TOC entry 2900 (class 0 OID 0)
-- Dependencies: 205
-- Name: mail_id_mail_seq; Type: SEQUENCE OWNED BY; Schema: rodobronca; Owner: postgres
--

ALTER SEQUENCE mail_id_mail_seq OWNED BY mail.id_mail;


--
-- TOC entry 206 (class 1259 OID 39074)
-- Name: mailbody; Type: TABLE; Schema: rodobronca; Owner: postgres
--

CREATE TABLE mailbody (
    mail_id integer NOT NULL,
    text text
);


ALTER TABLE mailbody OWNER TO postgres;

--
-- TOC entry 207 (class 1259 OID 39080)
-- Name: payment; Type: TABLE; Schema: rodobronca; Owner: postgres
--

CREATE TABLE payment (
    id_payment integer NOT NULL,
    charge double precision,
    date date,
    paid boolean,
    title character varying(255),
    allotment_user_id integer NOT NULL
);


ALTER TABLE payment OWNER TO postgres;

--
-- TOC entry 208 (class 1259 OID 39083)
-- Name: payment_id_payment_seq; Type: SEQUENCE; Schema: rodobronca; Owner: postgres
--

CREATE SEQUENCE payment_id_payment_seq
    AS integer
START WITH 1
INCREMENT BY 1
NO MINVALUE
NO MAXVALUE
CACHE 1;


ALTER TABLE payment_id_payment_seq OWNER TO postgres;

--
-- TOC entry 2901 (class 0 OID 0)
-- Dependencies: 208
-- Name: payment_id_payment_seq; Type: SEQUENCE OWNED BY; Schema: rodobronca; Owner: postgres
--

ALTER SEQUENCE payment_id_payment_seq OWNED BY payment.id_payment;


--
-- TOC entry 209 (class 1259 OID 39085)
-- Name: recipient; Type: TABLE; Schema: rodobronca; Owner: postgres
--

CREATE TABLE recipient (
    id_recipient bigint NOT NULL,
    seen boolean,
    mail_id bigint NOT NULL,
    user_reciver_id integer NOT NULL
);


ALTER TABLE recipient OWNER TO postgres;

--
-- TOC entry 210 (class 1259 OID 39088)
-- Name: recipient_id_recipient_seq; Type: SEQUENCE; Schema: rodobronca; Owner: postgres
--

CREATE SEQUENCE recipient_id_recipient_seq
START WITH 1
INCREMENT BY 1
NO MINVALUE
NO MAXVALUE
CACHE 1;


ALTER TABLE recipient_id_recipient_seq OWNER TO postgres;

--
-- TOC entry 2902 (class 0 OID 0)
-- Dependencies: 210
-- Name: recipient_id_recipient_seq; Type: SEQUENCE OWNED BY; Schema: rodobronca; Owner: postgres
--

ALTER SEQUENCE recipient_id_recipient_seq OWNED BY recipient.id_recipient;


--
-- TOC entry 211 (class 1259 OID 39090)
-- Name: role; Type: TABLE; Schema: rodobronca; Owner: postgres
--

CREATE TABLE role (
    id_role integer NOT NULL,
    name character varying(255)
);


ALTER TABLE role OWNER TO postgres;

--
-- TOC entry 212 (class 1259 OID 39093)
-- Name: role_id_role_seq; Type: SEQUENCE; Schema: rodobronca; Owner: postgres
--

CREATE SEQUENCE role_id_role_seq
    AS integer
START WITH 1
INCREMENT BY 1
NO MINVALUE
NO MAXVALUE
CACHE 1;


ALTER TABLE role_id_role_seq OWNER TO postgres;

--
-- TOC entry 2903 (class 0 OID 0)
-- Dependencies: 212
-- Name: role_id_role_seq; Type: SEQUENCE OWNED BY; Schema: rodobronca; Owner: postgres
--

ALTER SEQUENCE role_id_role_seq OWNED BY role.id_role;


--
-- TOC entry 213 (class 1259 OID 39095)
-- Name: user; Type: TABLE; Schema: rodobronca; Owner: postgres
--

CREATE TABLE "user" (
    id_user integer NOT NULL,
    is_active boolean NOT NULL,
    email character varying(255) NOT NULL,
    name character varying(255) NOT NULL,
    password character varying(255),
    phone character varying(255),
    last_name character varying(255) NOT NULL,
    role_id integer NOT NULL,
    username character varying(30) NOT NULL
);


ALTER TABLE "user" OWNER TO postgres;

--
-- TOC entry 214 (class 1259 OID 39101)
-- Name: user_id_user_seq; Type: SEQUENCE; Schema: rodobronca; Owner: postgres
--

CREATE SEQUENCE user_id_user_seq
    AS integer
START WITH 1
INCREMENT BY 1
NO MINVALUE
NO MAXVALUE
CACHE 1;


ALTER TABLE user_id_user_seq OWNER TO postgres;

--
-- TOC entry 2904 (class 0 OID 0)
-- Dependencies: 214
-- Name: user_id_user_seq; Type: SEQUENCE OWNED BY; Schema: rodobronca; Owner: postgres
--

ALTER SEQUENCE user_id_user_seq OWNED BY "user".id_user;


--
-- TOC entry 2726 (class 2604 OID 39103)
-- Name: allotment id_allotment; Type: DEFAULT; Schema: rodobronca; Owner: postgres
--

ALTER TABLE ONLY allotment ALTER COLUMN id_allotment SET DEFAULT nextval('allotment_id_allotment_seq'::regclass);


--
-- TOC entry 2728 (class 2604 OID 39104)
-- Name: article id_article; Type: DEFAULT; Schema: rodobronca; Owner: postgres
--

ALTER TABLE ONLY article ALTER COLUMN id_article SET DEFAULT nextval('article_id_article_seq'::regclass);


--
-- TOC entry 2729 (class 2604 OID 39105)
-- Name: commentary id_commentary; Type: DEFAULT; Schema: rodobronca; Owner: postgres
--

ALTER TABLE ONLY commentary ALTER COLUMN id_commentary SET DEFAULT nextval('commentary_id_commentary_seq'::regclass);


--
-- TOC entry 2730 (class 2604 OID 39106)
-- Name: mail id_mail; Type: DEFAULT; Schema: rodobronca; Owner: postgres
--

ALTER TABLE ONLY mail ALTER COLUMN id_mail SET DEFAULT nextval('mail_id_mail_seq'::regclass);


--
-- TOC entry 2731 (class 2604 OID 39107)
-- Name: payment id_payment; Type: DEFAULT; Schema: rodobronca; Owner: postgres
--

ALTER TABLE ONLY payment ALTER COLUMN id_payment SET DEFAULT nextval('payment_id_payment_seq'::regclass);


--
-- TOC entry 2732 (class 2604 OID 39108)
-- Name: recipient id_recipient; Type: DEFAULT; Schema: rodobronca; Owner: postgres
--

ALTER TABLE ONLY recipient ALTER COLUMN id_recipient SET DEFAULT nextval('recipient_id_recipient_seq'::regclass);


--
-- TOC entry 2733 (class 2604 OID 39109)
-- Name: role id_role; Type: DEFAULT; Schema: rodobronca; Owner: postgres
--

ALTER TABLE ONLY role ALTER COLUMN id_role SET DEFAULT nextval('role_id_role_seq'::regclass);


--
-- TOC entry 2734 (class 2604 OID 39110)
-- Name: user id_user; Type: DEFAULT; Schema: rodobronca; Owner: postgres
--

ALTER TABLE ONLY "user" ALTER COLUMN id_user SET DEFAULT nextval('user_id_user_seq'::regclass);


--
-- TOC entry 2736 (class 2606 OID 39112)
-- Name: allotment allotment_pkey; Type: CONSTRAINT; Schema: rodobronca; Owner: postgres
--

ALTER TABLE ONLY allotment
    ADD CONSTRAINT allotment_pkey PRIMARY KEY (id_allotment);


--
-- TOC entry 2738 (class 2606 OID 39114)
-- Name: allotment_user allotment_user_pkey; Type: CONSTRAINT; Schema: rodobronca; Owner: postgres
--

ALTER TABLE ONLY allotment_user
    ADD CONSTRAINT allotment_user_pkey PRIMARY KEY (id_allotment_user);


--
-- TOC entry 2742 (class 2606 OID 39116)
-- Name: article article_pkey; Type: CONSTRAINT; Schema: rodobronca; Owner: postgres
--

ALTER TABLE ONLY article
    ADD CONSTRAINT article_pkey PRIMARY KEY (id_article);


--
-- TOC entry 2744 (class 2606 OID 39118)
-- Name: commentary commentary_pkey; Type: CONSTRAINT; Schema: rodobronca; Owner: postgres
--

ALTER TABLE ONLY commentary
    ADD CONSTRAINT commentary_pkey PRIMARY KEY (id_commentary);


--
-- TOC entry 2746 (class 2606 OID 39120)
-- Name: mail mail_pkey; Type: CONSTRAINT; Schema: rodobronca; Owner: postgres
--

ALTER TABLE ONLY mail
    ADD CONSTRAINT mail_pkey PRIMARY KEY (id_mail);


--
-- TOC entry 2748 (class 2606 OID 39122)
-- Name: mailbody mailbody_pkey; Type: CONSTRAINT; Schema: rodobronca; Owner: postgres
--

ALTER TABLE ONLY mailbody
    ADD CONSTRAINT mailbody_pkey PRIMARY KEY (mail_id);


--
-- TOC entry 2750 (class 2606 OID 39124)
-- Name: payment payment_pkey; Type: CONSTRAINT; Schema: rodobronca; Owner: postgres
--

ALTER TABLE ONLY payment
    ADD CONSTRAINT payment_pkey PRIMARY KEY (id_payment);


--
-- TOC entry 2752 (class 2606 OID 39126)
-- Name: recipient recipient_pkey; Type: CONSTRAINT; Schema: rodobronca; Owner: postgres
--

ALTER TABLE ONLY recipient
    ADD CONSTRAINT recipient_pkey PRIMARY KEY (id_recipient);


--
-- TOC entry 2754 (class 2606 OID 39128)
-- Name: role role_pkey; Type: CONSTRAINT; Schema: rodobronca; Owner: postgres
--

ALTER TABLE ONLY role
    ADD CONSTRAINT role_pkey PRIMARY KEY (id_role);


--
-- TOC entry 2740 (class 2606 OID 39130)
-- Name: allotment_user unique_allotmentId_userId; Type: CONSTRAINT; Schema: rodobronca; Owner: postgres
--

ALTER TABLE ONLY allotment_user
    ADD CONSTRAINT "unique_allotmentId_userId" UNIQUE (allotment_id, user_id);


--
-- TOC entry 2756 (class 2606 OID 39132)
-- Name: user unique_username; Type: CONSTRAINT; Schema: rodobronca; Owner: postgres
--

ALTER TABLE ONLY "user"
    ADD CONSTRAINT unique_username UNIQUE (username);


--
-- TOC entry 2758 (class 2606 OID 39134)
-- Name: user user_pkey; Type: CONSTRAINT; Schema: rodobronca; Owner: postgres
--

ALTER TABLE ONLY "user"
    ADD CONSTRAINT user_pkey PRIMARY KEY (id_user);


--
-- TOC entry 2762 (class 2606 OID 39135)
-- Name: commentary fk4gnkpacv6jppejtkqdef1fkq4; Type: FK CONSTRAINT; Schema: rodobronca; Owner: postgres
--

ALTER TABLE ONLY commentary
    ADD CONSTRAINT fk4gnkpacv6jppejtkqdef1fkq4 FOREIGN KEY (user_id) REFERENCES "user"(id_user);


--
-- TOC entry 2764 (class 2606 OID 39140)
-- Name: mail fk4pothb0cit4co52nqkurg2u3o; Type: FK CONSTRAINT; Schema: rodobronca; Owner: postgres
--

ALTER TABLE ONLY mail
    ADD CONSTRAINT fk4pothb0cit4co52nqkurg2u3o FOREIGN KEY (user_sender_id) REFERENCES "user"(id_user);


--
-- TOC entry 2770 (class 2606 OID 39145)
-- Name: user fk84qlpfci484r1luck11eno6ec; Type: FK CONSTRAINT; Schema: rodobronca; Owner: postgres
--

ALTER TABLE ONLY "user"
    ADD CONSTRAINT fk84qlpfci484r1luck11eno6ec FOREIGN KEY (role_id) REFERENCES role(id_role);


--
-- TOC entry 2765 (class 2606 OID 39150)
-- Name: mailbody fk_mail_id; Type: FK CONSTRAINT; Schema: rodobronca; Owner: postgres
--

ALTER TABLE ONLY mailbody
    ADD CONSTRAINT fk_mail_id FOREIGN KEY (mail_id) REFERENCES mail(id_mail);


--
-- TOC entry 2768 (class 2606 OID 39155)
-- Name: recipient fkacrhlwys4a8d93dfc5yds7o3m; Type: FK CONSTRAINT; Schema: rodobronca; Owner: postgres
--

ALTER TABLE ONLY recipient
    ADD CONSTRAINT fkacrhlwys4a8d93dfc5yds7o3m FOREIGN KEY (user_reciver_id) REFERENCES "user"(id_user);


--
-- TOC entry 2767 (class 2606 OID 39160)
-- Name: payment fkaurylb3qlklrf2f474a0f2nfr; Type: FK CONSTRAINT; Schema: rodobronca; Owner: postgres
--

ALTER TABLE ONLY payment
    ADD CONSTRAINT fkaurylb3qlklrf2f474a0f2nfr FOREIGN KEY (allotment_user_id) REFERENCES allotment_user(id_allotment_user);


--
-- TOC entry 2759 (class 2606 OID 39165)
-- Name: allotment_user fkbvk384t791jqi1lwi0u91pce0; Type: FK CONSTRAINT; Schema: rodobronca; Owner: postgres
--

ALTER TABLE ONLY allotment_user
    ADD CONSTRAINT fkbvk384t791jqi1lwi0u91pce0 FOREIGN KEY (allotment_id) REFERENCES allotment(id_allotment);


--
-- TOC entry 2761 (class 2606 OID 39170)
-- Name: article fkkpi7de60p3npqbdh8yi0taf7x; Type: FK CONSTRAINT; Schema: rodobronca; Owner: postgres
--

ALTER TABLE ONLY article
    ADD CONSTRAINT fkkpi7de60p3npqbdh8yi0taf7x FOREIGN KEY (user_id) REFERENCES "user"(id_user);


--
-- TOC entry 2763 (class 2606 OID 39175)
-- Name: commentary fklpgd0p65bfd9syhlalvq6do4x; Type: FK CONSTRAINT; Schema: rodobronca; Owner: postgres
--

ALTER TABLE ONLY commentary
    ADD CONSTRAINT fklpgd0p65bfd9syhlalvq6do4x FOREIGN KEY (article_id) REFERENCES article(id_article);


--
-- TOC entry 2760 (class 2606 OID 39180)
-- Name: allotment_user fkmiq081vb3av0yy05xf60gg6gr; Type: FK CONSTRAINT; Schema: rodobronca; Owner: postgres
--

ALTER TABLE ONLY allotment_user
    ADD CONSTRAINT fkmiq081vb3av0yy05xf60gg6gr FOREIGN KEY (user_id) REFERENCES "user"(id_user);


--
-- TOC entry 2769 (class 2606 OID 39185)
-- Name: recipient fkq8i5u9sa3xreiqnouecqty4to; Type: FK CONSTRAINT; Schema: rodobronca; Owner: postgres
--

ALTER TABLE ONLY recipient
    ADD CONSTRAINT fkq8i5u9sa3xreiqnouecqty4to FOREIGN KEY (mail_id) REFERENCES mail(id_mail);


--
-- TOC entry 2766 (class 2606 OID 39190)
-- Name: mailbody fktm5qtbfktx18dq48aawjijys2; Type: FK CONSTRAINT; Schema: rodobronca; Owner: postgres
--

ALTER TABLE ONLY mailbody
    ADD CONSTRAINT fktm5qtbfktx18dq48aawjijys2 FOREIGN KEY (mail_id) REFERENCES mail(id_mail);


-- Completed on 2018-05-09 23:38:38

--
-- PostgreSQL database dump complete
--

