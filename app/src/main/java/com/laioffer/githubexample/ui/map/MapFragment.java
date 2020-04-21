package com.laioffer.githubexample.ui.map;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.laioffer.githubexample.R;
import com.laioffer.githubexample.base.BaseFragment;
import com.laioffer.githubexample.databinding.CustomMapInfoWindowBinding;
import com.laioffer.githubexample.databinding.MapFragmentBinding;
import com.laioffer.githubexample.remote.response.Job;
import com.laioffer.githubexample.ui.HomeList.HomeListFragment;
import com.laioffer.githubexample.ui.NavigationManager;
import com.laioffer.githubexample.util.Config;
import com.laioffer.githubexample.util.Utils;

import java.util.ArrayList;
import java.util.List;

public class MapFragment extends BaseFragment<MapViewModel, MapRepository>
        implements OnMapReadyCallback {

    private MapFragmentBinding binding;
    private MapView mapView;
    private GoogleMap googleMap;
    private NavigationManager navigationManager;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding =  MapFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mapView = (MapView) view.findViewById(R.id.event_map_view);
        if (mapView != null) {
            mapView.onCreate(null);
            mapView.onResume();
            mapView.getMapAsync(this);
        }
        FloatingActionButton fab = view.findViewById(R.id.fab_return);
        fab.setOnClickListener( v -> {
            navigationManager.navigateTo(new HomeListFragment());
        });
        FloatingActionButton returnFab = view.findViewById(R.id.fab_center);
        returnFab.setOnClickListener( v -> {
            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(new LatLng(Config.lat, Config.lon))
                    .zoom(10)
                    .build();

            googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        });
        viewModel.getListMutableLiveData().observe(getViewLifecycleOwner(), list -> {
            ArrayList<Marker> markerList = new ArrayList<>();
            if (list == null) {
                Utils.constructToast(getContext(), "Null List!").show();
                return;
            }
            for (Job job : list) {
                LatLng position = new LatLng(job.location.latitude, job.location.longitude);
                MarkerOptions markerOptions = new MarkerOptions()
                        .position(position)
                        .title(job.name)
                        .snippet(job.company)
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
                googleMap.addMarker(markerOptions);
                Marker marker = googleMap.addMarker(markerOptions);
                markerList.add(marker);
            }
            googleMap.setOnInfoWindowClickListener(marker -> {
                for (int i = 0; i < markerList.size(); i++) {
                    if (marker.getTitle().equals(markerList.get(i).getTitle())) {
                        Utils.constructToast(getContext(), list.get(i).address).show();
                    }
                }
            });
        });
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        navigationManager = (NavigationManager) context;
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        MapsInitializer.initialize(getContext());
        this.googleMap = googleMap;
        this.googleMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(getActivity(), R.raw.style_json));

        LatLng position = new LatLng(Config.lat, Config.lon);
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(position)
                .zoom(10)
                .build();

        googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        MarkerOptions markerOptions = new MarkerOptions().position(position).title("Me");
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
        googleMap.addMarker(markerOptions);
    }

    @Override
    protected MapViewModel getViewModel() {
        return new ViewModelProvider(requireActivity(), getFactory()).get(MapViewModel.class);
    }

    @Override
    protected ViewModelProvider.Factory getFactory() {
        return new ViewModelProvider.Factory() {
            @NonNull
            @Override
            public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
                return (T) new MapViewModel(getRepository());
            }
        };
    }

    @Override
    protected MapRepository getRepository() {
        return new MapRepository();
    }

}
