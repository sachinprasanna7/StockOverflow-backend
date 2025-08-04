package com.practice.StockOverflowBackend;

import com.practice.StockOverflowBackend.entities.Watchlist;
import com.practice.StockOverflowBackend.repositories.WatchlistRepository;
import com.practice.StockOverflowBackend.services.WatchlistService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class WatchlistServiceTest {

    @Mock
    private WatchlistRepository watchlistRepository;

    @InjectMocks
    private WatchlistService watchlistService;

    @Test
    void shouldReturnAllWatchlists() {
        List<Watchlist> mockList = List.of(
                new Watchlist(1, "watch1"),
                new Watchlist(2, "watch2")
        );
        when(watchlistRepository.findAll()).thenReturn(mockList);

        List<Watchlist> result = watchlistService.getWatchlists();

        assertEquals(2, result.size());
        assertEquals("watch1", result.get(0).getWatchlistName());
        assertEquals("watch2", result.get(1).getWatchlistName());
    }

    @Test
    void shouldAddWatchlistIfNameIsUnique() {
        String name = "uniqueList";
        when(watchlistRepository.existsByName(name)).thenReturn(false);

        watchlistService.addWatchlist(name);

        verify(watchlistRepository, times(1)).save(argThat(w -> w.getWatchlistName().equals(name)));
    }

    @Test
    void shouldThrowIfNameAlreadyExists() {
        String name = "existingList";
        when(watchlistRepository.existsByName(name)).thenReturn(true);

        assertThrows(ResponseStatusException.class, () -> {
            watchlistService.addWatchlist(name);
        });

        verify(watchlistRepository, never()).save(any());
    }

    @Test
    void shouldDeleteExistingWatchlist() {
        int id = 1;
        when(watchlistRepository.existsById(id)).thenReturn(true);

        watchlistService.deleteWatchlistById(id);

        verify(watchlistRepository, times(1)).deleteById(id);
    }

    @Test
    void shouldThrowWhenDeletingNonexistentWatchlist() {
        int nonExistentId = 99;
        when(watchlistRepository.existsById(nonExistentId)).thenReturn(false);

        assertThrows(ResponseStatusException.class, () -> {
            watchlistService.deleteWatchlistById(nonExistentId);
        });

        verify(watchlistRepository, never()).deleteById(anyInt());
    }
}
