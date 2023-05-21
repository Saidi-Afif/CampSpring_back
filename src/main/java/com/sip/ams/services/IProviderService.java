package com.sip.ams.services;

import com.sip.ams.entities.Provider;

import java.util.List;

public interface IProviderService {


    List<Provider> getAllProviders();

    Provider saveProvider(Provider provider);

    Provider getOneProviderById(long idProvider);

    Provider deleteProvider(long idProvider);

    Provider updateProvider(long idProvider, Provider provider);

}
