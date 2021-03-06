package com.game.service.impl;

import com.game.entity.Player;
import com.game.repository.PlayerRepository;
import com.game.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

@Service
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepository;

    @Autowired
    public PlayerServiceImpl(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Override
    public List<Player> getAll() {
        return playerRepository.findAll();
    }

    @Override
    public List<Player> getCountPlayers() {
        return playerRepository.findAll();
    }

    @Override
    public Player getById(Long id) {
        return playerRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(Long id) {
        playerRepository.deleteById(id);
    }

    @Override
    public void create(Player player) {
        player.calculateLevel(player.getExperience());
        playerRepository.save(player);
    }

    @Override
    public void update(Player player) {
        playerRepository.save(player);
    }

    @Override
    public Boolean checkedCreateAndUpdatePlayer(Player player) {
        if (player.getName().length() < 1 || player.getName().length() > 12
                || player.getTitle().length() < 1 || player.getTitle().length() > 30
                || player.getName().isEmpty()
                || player.getExperience() < 0 || player.getExperience() > 10_000_000
                || player.getBirthday().getTime() < 0
                || player.getBirthday().before(new GregorianCalendar(2000, Calendar.JANUARY, 1).getTime()) ||
                player.getBirthday().after(new GregorianCalendar(3000, Calendar.DECEMBER, 31).getTime())
        ) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public Boolean checkId(Long id) {
        if (id == null || id <= 0L || id % 1 != 0L) {
            return false;
        }
        return true;
    }
}
