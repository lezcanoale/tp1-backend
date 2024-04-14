PGDMP                         |            tp1-backend    12.18    15.2 ?    _           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            `           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            a           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            b           1262    16408    tp1-backend    DATABASE     �   CREATE DATABASE "tp1-backend" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'Spanish_Spain.1252';
    DROP DATABASE "tp1-backend";
                postgres    false                        2615    2200    public    SCHEMA     2   -- *not* creating schema, since initdb creates it
 2   -- *not* dropping schema, since initdb creates it
                postgres    false            c           0    0    SCHEMA public    ACL     Q   REVOKE USAGE ON SCHEMA public FROM PUBLIC;
GRANT ALL ON SCHEMA public TO PUBLIC;
                   postgres    false    6            �            1259    16509    bolsa_puntos    TABLE     �  CREATE TABLE public.bolsa_puntos (
    id bigint NOT NULL,
    id_cliente bigint NOT NULL,
    fecha_asigancion_puntaje date NOT NULL,
    fecha_caducidad_puntaje timestamp(6) without time zone NOT NULL,
    puntaje_asignado integer NOT NULL,
    puntaje_utilizado integer NOT NULL,
    saldo_puntos integer NOT NULL,
    monto_operacion bigint NOT NULL,
    fecha_asignacion_puntaje timestamp(6) without time zone NOT NULL
);
     DROP TABLE public.bolsa_puntos;
       public         heap    postgres    false    6            �            1259    16627    bolsa_puntos_detalles    TABLE     t   CREATE TABLE public.bolsa_puntos_detalles (
    bolsa_puntos_id bigint NOT NULL,
    detalles_id bigint NOT NULL
);
 )   DROP TABLE public.bolsa_puntos_detalles;
       public         heap    postgres    false    6            �            1259    16507    bolsa_puntos_id_cliente_seq    SEQUENCE     �   CREATE SEQUENCE public.bolsa_puntos_id_cliente_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 2   DROP SEQUENCE public.bolsa_puntos_id_cliente_seq;
       public          postgres    false    212    6            d           0    0    bolsa_puntos_id_cliente_seq    SEQUENCE OWNED BY     [   ALTER SEQUENCE public.bolsa_puntos_id_cliente_seq OWNED BY public.bolsa_puntos.id_cliente;
          public          postgres    false    211            �            1259    16505    bolsa_puntos_id_seq    SEQUENCE     �   CREATE SEQUENCE public.bolsa_puntos_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 *   DROP SEQUENCE public.bolsa_puntos_id_seq;
       public          postgres    false    6    212            e           0    0    bolsa_puntos_id_seq    SEQUENCE OWNED BY     K   ALTER SEQUENCE public.bolsa_puntos_id_seq OWNED BY public.bolsa_puntos.id;
          public          postgres    false    210            �            1259    16411    cliente    TABLE     �  CREATE TABLE public.cliente (
    id bigint NOT NULL,
    nombre character varying(255) NOT NULL,
    apellido character varying(255) NOT NULL,
    numero_documento character varying(255) NOT NULL,
    tipo_documento character varying(50) NOT NULL,
    nacionalidad character varying(255) NOT NULL,
    email character varying(255) NOT NULL,
    telefono character varying(255) NOT NULL,
    fecha_nacimiento timestamp(6) without time zone NOT NULL
);
    DROP TABLE public.cliente;
       public         heap    postgres    false    6            �            1259    16630    cliente_bolsas_de_puntos    TABLE     z   CREATE TABLE public.cliente_bolsas_de_puntos (
    cliente_id bigint NOT NULL,
    bolsas_de_puntos_id bigint NOT NULL
);
 ,   DROP TABLE public.cliente_bolsas_de_puntos;
       public         heap    postgres    false    6            �            1259    16409    cliente_id_seq    SEQUENCE     �   CREATE SEQUENCE public.cliente_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 %   DROP SEQUENCE public.cliente_id_seq;
       public          postgres    false    203    6            f           0    0    cliente_id_seq    SEQUENCE OWNED BY     A   ALTER SEQUENCE public.cliente_id_seq OWNED BY public.cliente.id;
          public          postgres    false    202            �            1259    16633    cliente_uso_puntos_cabeceras    TABLE     �   CREATE TABLE public.cliente_uso_puntos_cabeceras (
    cliente_id bigint NOT NULL,
    uso_puntos_cabeceras_id bigint NOT NULL
);
 0   DROP TABLE public.cliente_uso_puntos_cabeceras;
       public         heap    postgres    false    6            �            1259    16447    concepto_uso    TABLE     0  CREATE TABLE public.concepto_uso (
    id bigint NOT NULL,
    descripcion_concepto character varying(255) NOT NULL,
    puntos_requeridos integer NOT NULL,
    descripcion_uso character varying(255) NOT NULL,
    CONSTRAINT concepto_uso_puntos_puntos_requeridos_check CHECK ((puntos_requeridos > 0))
);
     DROP TABLE public.concepto_uso;
       public         heap    postgres    false    6            �            1259    16445    concepto_uso_puntos_id_seq    SEQUENCE     �   CREATE SEQUENCE public.concepto_uso_puntos_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 1   DROP SEQUENCE public.concepto_uso_puntos_id_seq;
       public          postgres    false    205    6            g           0    0    concepto_uso_puntos_id_seq    SEQUENCE OWNED BY     R   ALTER SEQUENCE public.concepto_uso_puntos_id_seq OWNED BY public.concepto_uso.id;
          public          postgres    false    204            �            1259    16491    regla_asignacion_puntos    TABLE     �   CREATE TABLE public.regla_asignacion_puntos (
    id bigint NOT NULL,
    limite_inferior bigint NOT NULL,
    limite_superior bigint NOT NULL,
    monto_punto bigint NOT NULL
);
 +   DROP TABLE public.regla_asignacion_puntos;
       public         heap    postgres    false    6            �            1259    16489    regla_asignacion_puntos_id_seq    SEQUENCE     �   CREATE SEQUENCE public.regla_asignacion_puntos_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 5   DROP SEQUENCE public.regla_asignacion_puntos_id_seq;
       public          postgres    false    207    6            h           0    0    regla_asignacion_puntos_id_seq    SEQUENCE OWNED BY     a   ALTER SEQUENCE public.regla_asignacion_puntos_id_seq OWNED BY public.regla_asignacion_puntos.id;
          public          postgres    false    206            �            1259    16565    uso_puntos_cabecera    TABLE     �   CREATE TABLE public.uso_puntos_cabecera (
    id bigint NOT NULL,
    id_cliente bigint NOT NULL,
    puntaje_utilizado integer NOT NULL,
    fecha timestamp(6) without time zone NOT NULL,
    id_concepto_uso bigint NOT NULL
);
 '   DROP TABLE public.uso_puntos_cabecera;
       public         heap    postgres    false    6            �            1259    16563    uso_puntos_cabecera_id_seq    SEQUENCE     �   CREATE SEQUENCE public.uso_puntos_cabecera_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 1   DROP SEQUENCE public.uso_puntos_cabecera_id_seq;
       public          postgres    false    6    216            i           0    0    uso_puntos_cabecera_id_seq    SEQUENCE OWNED BY     Y   ALTER SEQUENCE public.uso_puntos_cabecera_id_seq OWNED BY public.uso_puntos_cabecera.id;
          public          postgres    false    215            �            1259    16553    uso_puntos_detalle    TABLE     �   CREATE TABLE public.uso_puntos_detalle (
    id bigint NOT NULL,
    id_cabecera integer NOT NULL,
    puntaje_utilizado integer NOT NULL,
    id_bolsa_puntos bigint NOT NULL,
    uso_puntos_cabecera_id bigint
);
 &   DROP TABLE public.uso_puntos_detalle;
       public         heap    postgres    false    6            �            1259    16551    uso_puntos_detalle_id_seq    SEQUENCE     �   CREATE SEQUENCE public.uso_puntos_detalle_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 0   DROP SEQUENCE public.uso_puntos_detalle_id_seq;
       public          postgres    false    214    6            j           0    0    uso_puntos_detalle_id_seq    SEQUENCE OWNED BY     W   ALTER SEQUENCE public.uso_puntos_detalle_id_seq OWNED BY public.uso_puntos_detalle.id;
          public          postgres    false    213            �            1259    16499    vencimiento_puntos    TABLE     �   CREATE TABLE public.vencimiento_puntos (
    id bigint NOT NULL,
    fecha_inicio_validez timestamp(6) without time zone NOT NULL,
    fecha_fin_validez timestamp(6) without time zone NOT NULL,
    duracion_dias_puntaje integer NOT NULL
);
 &   DROP TABLE public.vencimiento_puntos;
       public         heap    postgres    false    6            �            1259    16497    vencimiento_puntos_id_seq    SEQUENCE     �   CREATE SEQUENCE public.vencimiento_puntos_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 0   DROP SEQUENCE public.vencimiento_puntos_id_seq;
       public          postgres    false    209    6            k           0    0    vencimiento_puntos_id_seq    SEQUENCE OWNED BY     W   ALTER SEQUENCE public.vencimiento_puntos_id_seq OWNED BY public.vencimiento_puntos.id;
          public          postgres    false    208            �
           2604    16601    bolsa_puntos id    DEFAULT     r   ALTER TABLE ONLY public.bolsa_puntos ALTER COLUMN id SET DEFAULT nextval('public.bolsa_puntos_id_seq'::regclass);
 >   ALTER TABLE public.bolsa_puntos ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    212    210    212            �
           2604    16617    bolsa_puntos id_cliente    DEFAULT     �   ALTER TABLE ONLY public.bolsa_puntos ALTER COLUMN id_cliente SET DEFAULT nextval('public.bolsa_puntos_id_cliente_seq'::regclass);
 F   ALTER TABLE public.bolsa_puntos ALTER COLUMN id_cliente DROP DEFAULT;
       public          postgres    false    211    212    212            �
           2604    16424 
   cliente id    DEFAULT     h   ALTER TABLE ONLY public.cliente ALTER COLUMN id SET DEFAULT nextval('public.cliente_id_seq'::regclass);
 9   ALTER TABLE public.cliente ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    203    202    203            �
           2604    16636    concepto_uso id    DEFAULT     y   ALTER TABLE ONLY public.concepto_uso ALTER COLUMN id SET DEFAULT nextval('public.concepto_uso_puntos_id_seq'::regclass);
 >   ALTER TABLE public.concepto_uso ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    204    205    205            �
           2604    16651    regla_asignacion_puntos id    DEFAULT     �   ALTER TABLE ONLY public.regla_asignacion_puntos ALTER COLUMN id SET DEFAULT nextval('public.regla_asignacion_puntos_id_seq'::regclass);
 I   ALTER TABLE public.regla_asignacion_puntos ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    206    207    207            �
           2604    16691    uso_puntos_detalle id    DEFAULT     ~   ALTER TABLE ONLY public.uso_puntos_detalle ALTER COLUMN id SET DEFAULT nextval('public.uso_puntos_detalle_id_seq'::regclass);
 D   ALTER TABLE public.uso_puntos_detalle ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    213    214    214            �
           2604    16707    vencimiento_puntos id    DEFAULT     ~   ALTER TABLE ONLY public.vencimiento_puntos ALTER COLUMN id SET DEFAULT nextval('public.vencimiento_puntos_id_seq'::regclass);
 D   ALTER TABLE public.vencimiento_puntos ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    208    209    209            �
           2606    16603    bolsa_puntos bolsa_puntos_pkey 
   CONSTRAINT     \   ALTER TABLE ONLY public.bolsa_puntos
    ADD CONSTRAINT bolsa_puntos_pkey PRIMARY KEY (id);
 H   ALTER TABLE ONLY public.bolsa_puntos DROP CONSTRAINT bolsa_puntos_pkey;
       public            postgres    false    212            �
           2606    16421    cliente cliente_email_key 
   CONSTRAINT     U   ALTER TABLE ONLY public.cliente
    ADD CONSTRAINT cliente_email_key UNIQUE (email);
 C   ALTER TABLE ONLY public.cliente DROP CONSTRAINT cliente_email_key;
       public            postgres    false    203            �
           2606    16426    cliente cliente_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.cliente
    ADD CONSTRAINT cliente_pkey PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.cliente DROP CONSTRAINT cliente_pkey;
       public            postgres    false    203            �
           2606    16638 %   concepto_uso concepto_uso_puntos_pkey 
   CONSTRAINT     c   ALTER TABLE ONLY public.concepto_uso
    ADD CONSTRAINT concepto_uso_puntos_pkey PRIMARY KEY (id);
 O   ALTER TABLE ONLY public.concepto_uso DROP CONSTRAINT concepto_uso_puntos_pkey;
       public            postgres    false    205            �
           2606    16653 4   regla_asignacion_puntos regla_asignacion_puntos_pkey 
   CONSTRAINT     r   ALTER TABLE ONLY public.regla_asignacion_puntos
    ADD CONSTRAINT regla_asignacion_puntos_pkey PRIMARY KEY (id);
 ^   ALTER TABLE ONLY public.regla_asignacion_puntos DROP CONSTRAINT regla_asignacion_puntos_pkey;
       public            postgres    false    207            �
           2606    16727 9   cliente_uso_puntos_cabeceras uk_bhhnfb59nx5cs8vnpnsn0yrbg 
   CONSTRAINT     �   ALTER TABLE ONLY public.cliente_uso_puntos_cabeceras
    ADD CONSTRAINT uk_bhhnfb59nx5cs8vnpnsn0yrbg UNIQUE (uso_puntos_cabeceras_id);
 c   ALTER TABLE ONLY public.cliente_uso_puntos_cabeceras DROP CONSTRAINT uk_bhhnfb59nx5cs8vnpnsn0yrbg;
       public            postgres    false    219            �
           2606    16725 5   cliente_bolsas_de_puntos uk_h26865sqnh7ge3fiwf42ytygt 
   CONSTRAINT        ALTER TABLE ONLY public.cliente_bolsas_de_puntos
    ADD CONSTRAINT uk_h26865sqnh7ge3fiwf42ytygt UNIQUE (bolsas_de_puntos_id);
 _   ALTER TABLE ONLY public.cliente_bolsas_de_puntos DROP CONSTRAINT uk_h26865sqnh7ge3fiwf42ytygt;
       public            postgres    false    218            �
           2606    16723 2   bolsa_puntos_detalles uk_ni8l0jmu2v47viybl442jw1mp 
   CONSTRAINT     t   ALTER TABLE ONLY public.bolsa_puntos_detalles
    ADD CONSTRAINT uk_ni8l0jmu2v47viybl442jw1mp UNIQUE (detalles_id);
 \   ALTER TABLE ONLY public.bolsa_puntos_detalles DROP CONSTRAINT uk_ni8l0jmu2v47viybl442jw1mp;
       public            postgres    false    217            �
           2606    16423    cliente uq_cliente_documento 
   CONSTRAINT     s   ALTER TABLE ONLY public.cliente
    ADD CONSTRAINT uq_cliente_documento UNIQUE (numero_documento, tipo_documento);
 F   ALTER TABLE ONLY public.cliente DROP CONSTRAINT uq_cliente_documento;
       public            postgres    false    203    203            �
           2606    16659 ,   uso_puntos_cabecera uso_puntos_cabecera_pkey 
   CONSTRAINT     j   ALTER TABLE ONLY public.uso_puntos_cabecera
    ADD CONSTRAINT uso_puntos_cabecera_pkey PRIMARY KEY (id);
 V   ALTER TABLE ONLY public.uso_puntos_cabecera DROP CONSTRAINT uso_puntos_cabecera_pkey;
       public            postgres    false    216            �
           2606    16693 *   uso_puntos_detalle uso_puntos_detalle_pkey 
   CONSTRAINT     h   ALTER TABLE ONLY public.uso_puntos_detalle
    ADD CONSTRAINT uso_puntos_detalle_pkey PRIMARY KEY (id);
 T   ALTER TABLE ONLY public.uso_puntos_detalle DROP CONSTRAINT uso_puntos_detalle_pkey;
       public            postgres    false    214            �
           2606    16709 *   vencimiento_puntos vencimiento_puntos_pkey 
   CONSTRAINT     h   ALTER TABLE ONLY public.vencimiento_puntos
    ADD CONSTRAINT vencimiento_puntos_pkey PRIMARY KEY (id);
 T   ALTER TABLE ONLY public.vencimiento_puntos DROP CONSTRAINT vencimiento_puntos_pkey;
       public            postgres    false    209            �
           2606    16738 3   cliente_bolsas_de_puntos fk25l99ljkbaoduuhiqqy0r83r    FK CONSTRAINT     �   ALTER TABLE ONLY public.cliente_bolsas_de_puntos
    ADD CONSTRAINT fk25l99ljkbaoduuhiqqy0r83r FOREIGN KEY (bolsas_de_puntos_id) REFERENCES public.bolsa_puntos(id);
 ]   ALTER TABLE ONLY public.cliente_bolsas_de_puntos DROP CONSTRAINT fk25l99ljkbaoduuhiqqy0r83r;
       public          postgres    false    2760    212    218            �
           2606    16758 .   uso_puntos_detalle fk36pek6x1bq00xk6aex5cfldu4    FK CONSTRAINT     �   ALTER TABLE ONLY public.uso_puntos_detalle
    ADD CONSTRAINT fk36pek6x1bq00xk6aex5cfldu4 FOREIGN KEY (uso_puntos_cabecera_id) REFERENCES public.uso_puntos_cabecera(id);
 X   ALTER TABLE ONLY public.uso_puntos_detalle DROP CONSTRAINT fk36pek6x1bq00xk6aex5cfldu4;
       public          postgres    false    216    2764    214            �
           2606    16743 4   cliente_bolsas_de_puntos fk3wawqkb54d2gto1hqp8vsynlx    FK CONSTRAINT     �   ALTER TABLE ONLY public.cliente_bolsas_de_puntos
    ADD CONSTRAINT fk3wawqkb54d2gto1hqp8vsynlx FOREIGN KEY (cliente_id) REFERENCES public.cliente(id);
 ^   ALTER TABLE ONLY public.cliente_bolsas_de_puntos DROP CONSTRAINT fk3wawqkb54d2gto1hqp8vsynlx;
       public          postgres    false    2750    218    203            �
           2606    16728 1   bolsa_puntos_detalles fk58lvrcxof4n2h6bcpvbvsn0d9    FK CONSTRAINT     �   ALTER TABLE ONLY public.bolsa_puntos_detalles
    ADD CONSTRAINT fk58lvrcxof4n2h6bcpvbvsn0d9 FOREIGN KEY (detalles_id) REFERENCES public.uso_puntos_detalle(id);
 [   ALTER TABLE ONLY public.bolsa_puntos_detalles DROP CONSTRAINT fk58lvrcxof4n2h6bcpvbvsn0d9;
       public          postgres    false    217    214    2762            �
           2606    16748 8   cliente_uso_puntos_cabeceras fk8w0gwg41xbor1m6p6ulad8ncn    FK CONSTRAINT     �   ALTER TABLE ONLY public.cliente_uso_puntos_cabeceras
    ADD CONSTRAINT fk8w0gwg41xbor1m6p6ulad8ncn FOREIGN KEY (uso_puntos_cabeceras_id) REFERENCES public.uso_puntos_cabecera(id);
 b   ALTER TABLE ONLY public.cliente_uso_puntos_cabeceras DROP CONSTRAINT fk8w0gwg41xbor1m6p6ulad8ncn;
       public          postgres    false    219    216    2764            �
           2606    16698 "   uso_puntos_detalle fk_bolsa_puntos    FK CONSTRAINT     �   ALTER TABLE ONLY public.uso_puntos_detalle
    ADD CONSTRAINT fk_bolsa_puntos FOREIGN KEY (id_bolsa_puntos) REFERENCES public.bolsa_puntos(id);
 L   ALTER TABLE ONLY public.uso_puntos_detalle DROP CONSTRAINT fk_bolsa_puntos;
       public          postgres    false    214    212    2760            �
           2606    16618    bolsa_puntos fk_cliente    FK CONSTRAINT     {   ALTER TABLE ONLY public.bolsa_puntos
    ADD CONSTRAINT fk_cliente FOREIGN KEY (id_cliente) REFERENCES public.cliente(id);
 A   ALTER TABLE ONLY public.bolsa_puntos DROP CONSTRAINT fk_cliente;
       public          postgres    false    203    2750    212            �
           2606    16673    uso_puntos_cabecera fk_cliente    FK CONSTRAINT     �   ALTER TABLE ONLY public.uso_puntos_cabecera
    ADD CONSTRAINT fk_cliente FOREIGN KEY (id_cliente) REFERENCES public.cliente(id) NOT VALID;
 H   ALTER TABLE ONLY public.uso_puntos_cabecera DROP CONSTRAINT fk_cliente;
       public          postgres    false    203    216    2750            �
           2606    16682 %   uso_puntos_cabecera fk_concepto_punto    FK CONSTRAINT     �   ALTER TABLE ONLY public.uso_puntos_cabecera
    ADD CONSTRAINT fk_concepto_punto FOREIGN KEY (id_concepto_uso) REFERENCES public.concepto_uso(id);
 O   ALTER TABLE ONLY public.uso_puntos_cabecera DROP CONSTRAINT fk_concepto_punto;
       public          postgres    false    2754    216    205            �
           2606    16660 )   uso_puntos_detalle fk_uso_puntos_cabecera    FK CONSTRAINT     �   ALTER TABLE ONLY public.uso_puntos_detalle
    ADD CONSTRAINT fk_uso_puntos_cabecera FOREIGN KEY (id_cabecera) REFERENCES public.uso_puntos_cabecera(id) NOT VALID;
 S   ALTER TABLE ONLY public.uso_puntos_detalle DROP CONSTRAINT fk_uso_puntos_cabecera;
       public          postgres    false    2764    216    214            �
           2606    16733 1   bolsa_puntos_detalles fkbcc0og9r2w0i1x6lxgu5d2fqs    FK CONSTRAINT     �   ALTER TABLE ONLY public.bolsa_puntos_detalles
    ADD CONSTRAINT fkbcc0og9r2w0i1x6lxgu5d2fqs FOREIGN KEY (bolsa_puntos_id) REFERENCES public.bolsa_puntos(id);
 [   ALTER TABLE ONLY public.bolsa_puntos_detalles DROP CONSTRAINT fkbcc0og9r2w0i1x6lxgu5d2fqs;
       public          postgres    false    212    217    2760            �
           2606    16753 8   cliente_uso_puntos_cabeceras fki9dv8t3kridethw0qesfu2koj    FK CONSTRAINT     �   ALTER TABLE ONLY public.cliente_uso_puntos_cabeceras
    ADD CONSTRAINT fki9dv8t3kridethw0qesfu2koj FOREIGN KEY (cliente_id) REFERENCES public.cliente(id);
 b   ALTER TABLE ONLY public.cliente_uso_puntos_cabeceras DROP CONSTRAINT fki9dv8t3kridethw0qesfu2koj;
       public          postgres    false    219    203    2750           