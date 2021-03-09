package io.oneo.agon.modules.profissional.service;

import io.oneo.agon.modules.common.service.BaseService;
import io.oneo.agon.modules.common.exception.BaseServiceException;
import io.oneo.agon.modules.profissional.mapper.ProfissionalMapper;
import io.oneo.agon.modules.profissional.model.Profissional;
import io.oneo.agon.modules.profissional.repository.ProfissionalRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

@ApplicationScoped
public class ProfissionalService extends BaseService<Profissional, Long>
{
    private final Logger logger = LoggerFactory.getLogger(ProfissionalService.class);

    @Inject ProfissionalRepository repo;

    @Inject ProfissionalMapper mapper;

    public ProfissionalMapper getMapper() { return this.mapper; }

    @Transactional
    public Profissional addEdit(Profissional profissional) throws BaseServiceException
    {
        try
        {
            if (profissional.getId() == null)
            {
                return this.create(profissional);
            }
            return this.update(profissional);
        }
        catch (Exception e)
        {
            logger.error(e.getMessage());
            throw new BaseServiceException("Erro ao gravar os dados do usuário", e);
        }
    }

}