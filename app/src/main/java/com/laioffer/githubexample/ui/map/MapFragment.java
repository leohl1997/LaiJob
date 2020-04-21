package com.laioffer.githubexample.ui.map;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

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
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Objects;

public class MapFragment extends BaseFragment<MapViewModel, MapRepository>
        implements OnMapReadyCallback, GoogleMap.InfoWindowAdapter,
        GoogleMap.OnInfoWindowClickListener {

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
            if (list != null) {
                addJobToMap(list);
            }
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
        MapsInitializer.initialize(Objects.requireNonNull(getContext()));
        this.googleMap = googleMap;
        this.googleMap.setMapStyle(MapStyleOptions
                .loadRawResourceStyle(Objects.requireNonNull(getActivity()), R.raw.style_json));

        LatLng position = new LatLng(Config.lat, Config.lon);
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(position)
                .zoom(10)
                .build();

        googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        MarkerOptions markerOptions = new MarkerOptions().position(position).title("Me");
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
        googleMap.addMarker(markerOptions);
        googleMap.setInfoWindowAdapter(this);
        googleMap.setOnInfoWindowClickListener(this);
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

    private void addJobToMap(List<Job> jobs) {
        if (jobs == null) {
            Utils.constructToast(getContext(), "Null List!").show();
            return;
        }

        for (Job job : jobs) {
            LatLng position = new LatLng(job.location.latitude, job.location.longitude);
            MarkerOptions markerOptions = new MarkerOptions()
                    .position(position)
                    .title(job.name)
                    .snippet(job.itemId)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
            googleMap.addMarker(markerOptions);
            Marker marker = googleMap.addMarker(markerOptions);
            marker.setTag(job);
        }

    }

    @Override
    public View getInfoWindow(Marker marker) {
        if (!(marker.getTag() instanceof Job)) {
            return null;
        }
        Job currJob = (Job) marker.getTag();
        if (currJob == null) {
            return null;
        }
        CustomMapInfoWindowBinding binding = CustomMapInfoWindowBinding.inflate(getLayoutInflater());
        binding.tvTitle.setText(currJob.name);
        binding.tvCompanyName.setText(currJob.company);
        binding.tvLocation.setText(currJob.address);
        if (!currJob.imageUrl.isEmpty()) {
            Picasso.get().setLoggingEnabled(true);
            Picasso.get().load(currJob.imageUrl).placeholder(R.drawable.ic_center)
                    .resize(100,100)
                    .into(binding.imgInfo);

        }
        return binding.getRoot();
    }

    @Override
    public View getInfoContents(Marker marker) {
        return null;
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        if (!(marker.getTag() instanceof Job)) {
            return;
        }
        Job currJob = (Job) marker.getTag();
        if (currJob == null) {
            return;
        }
        Utils.constructToast(getContext(), currJob.name).show();
    }

}
