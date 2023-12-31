# CLASSRD
    INSERT INTO <SCH>CLASSRD (CLASS_NAME, RELATION_NAME, CORR_CLASS_NAME, OUTER_JOIN, ONLY_SELECT, XMLEXPORTABLE, GRID_DISPLAYABLE, DEF_SETTING_POS, USER_ID, CHECK_DISABLED) VALUES ('YProdottiInseriti', 'Articolo', 'Articolo', 'N', 'N', 'N', 'Y', 6, <USER>, 'N');
    INSERT INTO <SCH>CLASSRD (CLASS_NAME, RELATION_NAME, CORR_CLASS_NAME, OUTER_JOIN, ONLY_SELECT, XMLEXPORTABLE, GRID_DISPLAYABLE, DEF_SETTING_POS, USER_ID, CHECK_DISABLED) VALUES ('YProdottiInseriti', 'Azienda', 'AziendaEstesa', 'N', 'N', 'N', 'N', 5, <USER>, 'N');
# CLASSRD_NLS
    INSERT INTO <SCH>CLASSRD_NLS (CLASS_NAME, RELATION_NAME, LANGUAGE, NLS_RELATION_NAME, NLS_COLUMN_TITLE, NLS_LABEL) VALUES ('YProdottiInseriti', 'Articolo', 'it', 'Articolo', 'Articolo', null);
    INSERT INTO <SCH>CLASSRD_NLS (CLASS_NAME, RELATION_NAME, LANGUAGE, NLS_RELATION_NAME, NLS_COLUMN_TITLE, NLS_LABEL) VALUES ('YProdottiInseriti', 'Azienda', 'it', 'Azienda', 'Azienda', null);
# CLASSRD_ROW
    INSERT INTO <SCH>CLASSRD_ROW (CLASS_NAME, RELATION_NAME, SEQUENCE, ATTRIBUTE_NAME, CD_NAME, CORR_CLASS_NAME, CORR_ATTR_NAME, DEF_SEARCH) VALUES ('YProdottiInseriti', 'Articolo', 2, 'RArticolo', null, 'Articolo', 'IdArticolo', 'N');
    INSERT INTO <SCH>CLASSRD_ROW (CLASS_NAME, RELATION_NAME, SEQUENCE, ATTRIBUTE_NAME, CD_NAME, CORR_CLASS_NAME, CORR_ATTR_NAME, DEF_SEARCH) VALUES ('YProdottiInseriti', 'Articolo', 1, 'IdAzienda', null, 'Articolo', 'IdAzienda', 'N');
    INSERT INTO <SCH>CLASSRD_ROW (CLASS_NAME, RELATION_NAME, SEQUENCE, ATTRIBUTE_NAME, CD_NAME, CORR_CLASS_NAME, CORR_ATTR_NAME, DEF_SEARCH) VALUES ('YProdottiInseriti', 'Articolo', 3, null, 'DatiComuniEstesi.Stato', 'DatiComuniEstesi', 'Stato', 'Y');
    INSERT INTO <SCH>CLASSRD_ROW (CLASS_NAME, RELATION_NAME, SEQUENCE, ATTRIBUTE_NAME, CD_NAME, CORR_CLASS_NAME, CORR_ATTR_NAME, DEF_SEARCH) VALUES ('YProdottiInseriti', 'Azienda', 2, null, null, 'AziendaEstesa', 'Descrizione', 'Y');
    INSERT INTO <SCH>CLASSRD_ROW (CLASS_NAME, RELATION_NAME, SEQUENCE, ATTRIBUTE_NAME, CD_NAME, CORR_CLASS_NAME, CORR_ATTR_NAME, DEF_SEARCH) VALUES ('YProdottiInseriti', 'Azienda', 1, 'IdAzienda', null, 'AziendaEstesa', 'IdAzienda', 'N');
