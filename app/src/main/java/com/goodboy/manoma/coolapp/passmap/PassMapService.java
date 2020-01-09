package com.goodboy.manoma.coolapp.passmap;

import android.util.Log;
import android.util.Pair;

import com.goodboy.manoma.coolapp.entity.Pass;
import com.goodboy.manoma.coolapp.entity.Player;
import com.goodboy.manoma.coolapp.entity.Position;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by mitsukim on 2019/11/28.
 */
public class PassMapService {
    private final List<Player> mPlayers;

    private static final PassMapService INSTANCE = new PassMapService();

    private PassMapService() {
        mPlayers = new LinkedList<>();
    }

    static PassMapService getInstance() {
        return INSTANCE;
    }

    void addPlayers(List players) {
        mPlayers.clear();
        mPlayers.addAll(players);
    }

    void setPosition(int number, Position position) {
        Player player = getPlayer(number);
        player.setPosition(position);
    }

    List<Pair<Integer, Integer>> getPassDetail(int currentNumber) {
        List<Pair<Integer, Integer>> passDetail = new LinkedList<>();
        for (Player player : mPlayers) {
            int number = player.getNumber();
            int numberOfPass = getPlayer(currentNumber).getNumberOfPassTo(player);
            passDetail.add(Pair.create(number, numberOfPass));
        }
        return passDetail;
    }

    /**
     * パスが0の場合、{@link Player} のパスに追加されない。
     * @param passDetails
     */
    void setPassDetail(int currentNumber, Map<Integer, Integer> passDetails) {
        Player currentPlayer = getPlayer(currentNumber);
        for (Map.Entry<Integer, Integer> passDetail : passDetails.entrySet()) {
            Player receivePlayer = getPlayer(passDetail.getKey());
            int numOfPass = currentPlayer.getNumberOfPassTo(receivePlayer);
            if (numOfPass != passDetail.getValue()) {
                Pass pass = new Pass(currentPlayer, receivePlayer, passDetail.getValue());
                currentPlayer.updatePass(pass);
            }
        }
    }

    Player getPlayer(int number) {
        for (Player player : mPlayers) {
            if (player.getNumber() == number) {
                return player;
            }
        }
        return null;
    }

    List<Pass> getAllPasses() {
        List<Pass> passes = new ArrayList<>();
        for (Player player : mPlayers) {
            for (Pass pass : player.getPasses()) {
                Pass sameRoutePass = getSamePassRoute(pass, passes);
                if (sameRoutePass != null) {
                    passes.remove(sameRoutePass);
                    Pass newPass = pass.merge(sameRoutePass);
                    passes.add(newPass);
                    continue;
                }
                passes.add(pass);
            }
        }
        Log.i("TAG", passes.size() + " passes size.");
        return passes;
    }

    /**
     * 同じルートのパスがなかったらnullを返す。
     * @param pass
     * @param mergedPasses
     * @return
     */
    private Pass getSamePassRoute(Pass pass, List<Pass> mergedPasses) {
        for (Pass mergedPass : mergedPasses) {
            if (pass.isSamePassRoute(mergedPass)) {
                return mergedPass;
            }
        }
        return null;
    }

    List<Player> getPlayers() {
        return mPlayers;
    }

}
