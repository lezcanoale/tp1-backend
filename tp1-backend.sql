PGDMP  -    -        
        |            tp1-backend    12.18    16.2                0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false                       0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false                       0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false                       1262    16408    tp1-backend    DATABASE     �   CREATE DATABASE "tp1-backend" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'Spanish_Spain.1252';
    DROP DATABASE "tp1-backend";
                postgres    false                        2615    2200    public    SCHEMA        CREATE SCHEMA public;
    DROP SCHEMA public;
                postgres    false                       0    0    SCHEMA public    COMMENT     6   COMMENT ON SCHEMA public IS 'standard public schema';
                   postgres    false    6                       0    0    SCHEMA public    ACL     Q   REVOKE USAGE ON SCHEMA public FROM PUBLIC;
GRANT ALL ON SCHEMA public TO PUBLIC;
                   postgres    false    6            �            1259    16411    cliente    TABLE     �  CREATE TABLE public.cliente (
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
       public         heap    postgres    false    6            �            1259    16409    cliente_id_seq    SEQUENCE     �   CREATE SEQUENCE public.cliente_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 %   DROP SEQUENCE public.cliente_id_seq;
       public          postgres    false    6    203                       0    0    cliente_id_seq    SEQUENCE OWNED BY     A   ALTER SEQUENCE public.cliente_id_seq OWNED BY public.cliente.id;
          public          postgres    false    202            �            1259    16447    concepto_uso_puntos    TABLE       CREATE TABLE public.concepto_uso_puntos (
    id integer NOT NULL,
    descripcion_concepto character varying(255) NOT NULL,
    puntos_requeridos integer NOT NULL,
    CONSTRAINT concepto_uso_puntos_puntos_requeridos_check CHECK ((puntos_requeridos > 0))
);
 '   DROP TABLE public.concepto_uso_puntos;
       public         heap    postgres    false    6            �            1259    16445    concepto_uso_puntos_id_seq    SEQUENCE     �   CREATE SEQUENCE public.concepto_uso_puntos_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 1   DROP SEQUENCE public.concepto_uso_puntos_id_seq;
       public          postgres    false    205    6                       0    0    concepto_uso_puntos_id_seq    SEQUENCE OWNED BY     Y   ALTER SEQUENCE public.concepto_uso_puntos_id_seq OWNED BY public.concepto_uso_puntos.id;
          public          postgres    false    204            �
           2604    16424 
   cliente id    DEFAULT     h   ALTER TABLE ONLY public.cliente ALTER COLUMN id SET DEFAULT nextval('public.cliente_id_seq'::regclass);
 9   ALTER TABLE public.cliente ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    202    203    203            �
           2604    16450    concepto_uso_puntos id    DEFAULT     �   ALTER TABLE ONLY public.concepto_uso_puntos ALTER COLUMN id SET DEFAULT nextval('public.concepto_uso_puntos_id_seq'::regclass);
 E   ALTER TABLE public.concepto_uso_puntos ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    204    205    205            �
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
           2606    16453 ,   concepto_uso_puntos concepto_uso_puntos_pkey 
   CONSTRAINT     j   ALTER TABLE ONLY public.concepto_uso_puntos
    ADD CONSTRAINT concepto_uso_puntos_pkey PRIMARY KEY (id);
 V   ALTER TABLE ONLY public.concepto_uso_puntos DROP CONSTRAINT concepto_uso_puntos_pkey;
       public            postgres    false    205            �
           2606    16423    cliente uq_cliente_documento 
   CONSTRAINT     s   ALTER TABLE ONLY public.cliente
    ADD CONSTRAINT uq_cliente_documento UNIQUE (numero_documento, tipo_documento);
 F   ALTER TABLE ONLY public.cliente DROP CONSTRAINT uq_cliente_documento;
       public            postgres    false    203    203           