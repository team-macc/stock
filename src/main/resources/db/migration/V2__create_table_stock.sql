CREATE TABLE stock
(
    id bigint NOT NULL DEFAULT nextval('stock_id_seq'::regclass),
    id_produto bigint NOT NULL,
    quantidade_produto integer NOT NULL,
    CONSTRAINT stock_pkey PRIMARY KEY (id)
);
