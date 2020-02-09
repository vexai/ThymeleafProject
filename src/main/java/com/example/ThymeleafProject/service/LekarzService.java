package com.example.ThymeleafProject.service;

import com.example.ThymeleafProject.LekarzController;
import com.example.ThymeleafProject.TestController;
import com.example.ThymeleafProject.model.SkierowanieDoLekarza;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class LekarzService {
    private Map<Integer, SkierowanieDoLekarza> skierowanieDoLekarzaMap = new HashMap<>();
    public SkierowanieDoLekarza createSkierowanieDoLekarza(String lekarz, String pacjent, Date termin) {
        int iD = new Random().nextInt();
        SkierowanieDoLekarza skierowanieDoLekarza = new SkierowanieDoLekarza(iD, lekarz, pacjent, termin);
        skierowanieDoLekarzaMap.put(iD, skierowanieDoLekarza);
        return skierowanieDoLekarza;
    }

    public void deleteSkierowanieDoLekarza(int iD) {
        skierowanieDoLekarzaMap.remove(iD);
    }

    public Collection<SkierowanieDoLekarza> listSkierowaniaDoLekarza() {
        return skierowanieDoLekarzaMap.values();
    }

    public SkierowanieDoLekarza getSkierowanieDoLekarza(int iD) {
        if (skierowanieDoLekarzaMap.get(iD) == null) {
            throw new LekarzController.NotFoundException();
        }
        return skierowanieDoLekarzaMap.get(iD);
    }

    public SkierowanieDoLekarza updateSkierowanie (SkierowanieDoLekarza skierowanieDoLekarza) {
        SkierowanieDoLekarza existing = getSkierowanieDoLekarza(skierowanieDoLekarza.getiD());
        existing.setLekarz(skierowanieDoLekarza.getLekarz());
        existing.setPacjent(skierowanieDoLekarza.getPacjent());
        existing.setTermin(skierowanieDoLekarza.getTermin());
        skierowanieDoLekarzaMap.put(existing.getiD(),existing);
        return existing;
    }

}
