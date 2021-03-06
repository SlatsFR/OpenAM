/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 2006 Sun Microsystems Inc. All Rights Reserved
 *
 * The contents of this file are subject to the terms
 * of the Common Development and Distribution License
 * (the License). You may not use this file except in
 * compliance with the License.
 *
 * You can obtain a copy of the License at
 * https://opensso.dev.java.net/public/CDDLv1.0.html or
 * opensso/legal/CDDLv1.0.txt
 * See the License for the specific language governing
 * permission and limitations under the License.
 *
 * When distributing Covered Code, include this CDDL
 * Header Notice in each file and include the License file
 * at opensso/legal/CDDLv1.0.txt.
 * If applicable, add the following below the CDDL Header,
 * with the fields enclosed by brackets [] replaced by
 * your own identifying information:
 * "Portions Copyrighted [year] [name of copyright owner]"
 *
 * $Id: IDPAccountMapper.java,v 1.5 2008/06/25 05:47:51 qcheng Exp $
 *
 * Portions Copyrighted 2015 ForgeRock AS.
 */
package com.sun.identity.saml2.plugins;

import com.sun.identity.saml2.assertion.NameID;
import com.sun.identity.saml2.common.SAML2Exception;
import com.sun.identity.saml2.protocol.ManageNameIDRequest;

/**
 * The interface <code>IDPAccountMapper</code> is used to map the local identities to the <code>SAML</code> protocol
 * objects and also the vice versa for some of the protocols for e.g. <code>ManageNameIDRequest</code>.
 * <p/>
 * This mapper interface is used to map the identities only at the <code>SAML Identity Provider</code>. The
 * implementation of this interface will be used by the <code>SAML</code> framework to retrieve the user's account
 * federation information for constructing SAML protocol objects such as <code>Assertion</code> and also to find out
 * the corresponding user account for the given SAML requests.
 *
 * @see com.sun.identity.saml2.plugins.SPAccountMapper
 * @supported.all.api
 */
public interface IDPAccountMapper {

    /**
     * Returns the user's <code>NameID</code>information that contains account federation with the corresponding remote
     * and local entities.
     *
     * @param session Single Sign On session of the user.
     * @param hostEntityID <code>EntityID</code> of the hosted provider.
     * @param remoteEntityID <code>EntityID</code> of the remote provider.
     * @param realm Realm or the organization name that may be used to find the user information.
     * @param nameIDFormat <code>NameID</code> format.
     * @return The <code>NameID</code> corresponding to the authenticated user.
     * @throws SAML2Exception If there was any failure.
     */
    public NameID getNameID(Object session, String hostEntityID, String remoteEntityID, String realm,
            String nameIDFormat) throws SAML2Exception;


    /**
     * Returns the user's distinguished name or the universal ID for the corresponding
     * <code>SAML ManageNameIDRequest</code>.
     * This method returns the universal ID or the DN based on the deployment of
     * the SAMLv2 plugin base platform.
     *
     * @param manageNameIDRequest <code>SAML ManageNameIDRequest</code> that needs to be mapped to the user.
     * @param hostEntityID <code>EntityID</code> of the hosted provider.
     * @param realm Realm or the organization name that may be used to find the user information.
     * @return User's distinguished name or the universal ID.
     * @throws SAML2Exception If there was any failure.
     */
    public String getIdentity(ManageNameIDRequest manageNameIDRequest, String hostEntityID, String realm)
            throws SAML2Exception;

    /**
     * Returns the user's distinguished name or the universal ID for the corresponding <code>SAML NameID</code>.
     * This method returns the universal ID or the DN based on the deployment of the SAMLv2 plugin base platform.
     *
     * @param nameID <code>SAML NameID</code> that needs to be mapped to the user.
     * @param hostEntityID <code>EntityID</code> of the hosted provider.
     * @param remoteEntityID <code>EntityID</code> of the remote provider.
     * @param realm Realm or the organization name that may be used to find the user information.
     * @return User's distinguished name or the universal ID.
     * @throws SAML2Exception If there was any failure.
     */
    public String getIdentity(NameID nameID, String hostEntityID, String remoteEntityID, String realm)
            throws SAML2Exception;

    /**
     * Tells whether the provided NameID-Format should be persisted in the user data store or not.
     *
     * @param realm The hosted IdP's realm.
     * @param hostEntityID The hosted IdP's entityID.
     * @param remoteEntityID The remote SP's entityID.
     * @param nameIDFormat The non-transient, non-persistent NameID-Format in question.
     * @return <code>true</code> if the provided NameID-Format should be persisted in the user data store,
     * <code>false</code> otherwise.
     */
    public boolean shouldPersistNameIDFormat(String realm, String hostEntityID, String remoteEntityID,
            String nameIDFormat);
}
