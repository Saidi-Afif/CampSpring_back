package com.sip.ams.implments;

import com.sip.ams.entities.Provider;
import com.sip.ams.repositories.ProviderRepository;
import com.sip.ams.services.IProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class ProviderServiceImpl implements IProviderService {
    @Autowired
    ProviderRepository providerRepository;

    @Override
    public List<Provider> getAllProviders() {
        return providerRepository.findAll();
    }

    @Override
    public Provider saveProvider(Provider provider) {
        return providerRepository.save(provider);
    }

    @Override
    public Provider getOneProviderById(long idProvider) {
        return providerRepository.findById(idProvider).orElseThrow(() -> new IllegalArgumentException("ProviderId " + idProvider + " not found"));
    }

    @Override
    public Provider deleteProvider(long idProvider) {
        Provider temp=null;
        Optional<Provider> opt = providerRepository.findById(idProvider);
        if(opt.isPresent())
        {
            temp = opt.get();
            providerRepository.delete(temp);
            return temp;
        }
        if(temp == null) throw new IllegalArgumentException("Provider with id = "+ idProvider+"not Found");
        return temp;
    }

    @Override
    public Provider updateProvider(long idProvider, Provider provider) {
        Provider temp = null;
        Optional<Provider> opt = providerRepository.findById(idProvider);
        if(opt.isPresent())
        {
            temp = opt.get();
            temp.setName(provider.getName());
            temp.setAddress(provider.getAddress());
            temp.setEmail(provider.getEmail());
            providerRepository.save(temp);
            return temp;
        }
        if(temp == null) throw new IllegalArgumentException("Provider with id = "+ idProvider+"not Found");
        return temp;
    }
}
